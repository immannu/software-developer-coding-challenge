package com.auction.TradeRevAuction.service;

import com.auction.TradeRevAuction.Exception.AuctionException;
import com.auction.TradeRevAuction.Validator.VehicleValidatorServiceImpl;
import com.auction.TradeRevAuction.model.Account;
import com.auction.TradeRevAuction.model.AuctionBid;
import com.auction.TradeRevAuction.model.AuctionStatus;
import com.auction.TradeRevAuction.model.Vehicle;
import com.auction.TradeRevAuction.model.VehicleAuction;
import com.auction.TradeRevAuction.model.VehicleAuctionHistory;
import com.auction.TradeRevAuction.repository.AccountRepository;
import com.auction.TradeRevAuction.repository.VehicleAuctionHistoryRepository;
import com.auction.TradeRevAuction.repository.VehicleAuctionRepository;
import com.auction.TradeRevAuction.repository.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

@Service
public class VehicleAuctionServiceImpl implements VehicleAuctionService {

  static Logger logger = LoggerFactory.getLogger(VehicleAuctionServiceImpl.class);


  private final AccountRepository accountRepository;
  private final VehicleRepository vehicleRepository;
  private final VehicleAuctionRepository vehicleAuctRepository;
  private final VehicleAuctionHistoryRepository vehicleAuctHistRepository;

  public VehicleAuctionServiceImpl(AccountRepository accountRepository, VehicleRepository vehicleRepository, VehicleAuctionRepository vehicleAuctRepository, VehicleAuctionHistoryRepository vehicleAuctHistRepository) {
    this.accountRepository = accountRepository;
    this.vehicleRepository = vehicleRepository;
    this.vehicleAuctRepository = vehicleAuctRepository;
    this.vehicleAuctHistRepository = vehicleAuctHistRepository;
  }

  @Override
  public VehicleAuction createAuction(int accountId, VehicleAuction auction) throws AuctionException {
    Vehicle vehicle = auction.getAucVehicle();
    validateAccountVehicle(accountId, vehicle);
    validateVehicleTitle(vehicle);
    validateAuction(auction, AuctionStatus.NOT_STARTED );
    VehicleAuction newAuction = createAuction(vehicle, auction);
    return newAuction;
  }

  private void validateAuction(VehicleAuction auction, AuctionStatus expected) throws AuctionException {
    logger.info("Verifying auction parameters");

    if(auction.getStatus()!= expected){
      throw new AuctionException("Invalid Auction Status, expected status  ${expected} but actual status is ${auction.getStatus()}");
    }

    if(auction.getAuctionEndTime().before(auction.getAuctionStartTime())){
      throw new AuctionException("Auction End time is earlier than Start");
    }

    if(auction.getBasePrice() <= 0){
      throw new AuctionException("Invalid BasePrice");
    }

    if(auction.getAuctionDurationHrs() <= 0){
      throw new AuctionException("Invalid Auction Duration");
    }

  }

  private VehicleAuction createAuction(Vehicle vehicle, VehicleAuction auction){
    return auction.toBuilder().aucVehicle(vehicle).basePrice(vehicle.getVehicleValue()).status(AuctionStatus.NOT_STARTED).build();
  }

  private void validateAccountVehicle(int accountId, Vehicle vehicle) throws AuctionException {

    logger.info("Verifying whether vehicle with id : ${vehicle.getId()} belong to account  ${accountId}");
    if(vehicle.getVehicleAccount().getAccount().getId() != accountId){
      throw new AuctionException("vehicle with id : ${vehicle.getId()} belong to account  ${accountId}");
    }
    logger.info("Verifying that vehicle with id : ${vehicle.getId()} belongs to account  ${accountId}");

  }

  private void validateVehicleTitle(Vehicle vehicle) throws AuctionException {
    logger.info("Verifying title of  vehicle with id : ${vehicle.getId()} ");
    VehicleValidatorServiceImpl.validateByVin(vehicle.getVin());
    logger.info("Verified title of vehicle with id : ${vehicle.getId()} ");
  }

  @Override
  public VehicleAuction startAuction(int accountId, VehicleAuction auction) throws AuctionException {

    validateAccountVehicle(accountId, auction.getAucVehicle());
    validateAuction(auction, AuctionStatus.NOT_STARTED );
    VehicleAuction newAuction = startAuction(auction);
    return newAuction;

  }

  private VehicleAuction startAuction(VehicleAuction auction){
    Timestamp startTime = auction.getAuctionStartTime();
    int durationHrs = auction.getAuctionDurationHrs();
    Timestamp endTime = new Timestamp(startTime.getTime() + (1000 * 60 * 60 * durationHrs));
    this.vehicleAuctHistRepository.save(VehicleAuctionHistory.builder()
                                  .acc(auction.getAccountVal())
                                  .basePrice(auction.getAucVehicle().getVehicleValue())
                                  .auctionPrice(auction.getAucVehicle().getVehicleValue())
                                  .vehAuc(auction).veh(auction.getAucVehicle()).build());

    return auction.toBuilder().status(AuctionStatus.IN_PROGRESS).auctionEndTime(endTime).build();
  }

  @Override
  public VehicleAuction updateAuction(int accountIdBidder, int auctionId, AuctionBid auctionBid ) throws AuctionException {
    Optional<VehicleAuction> vehicleAuction = vehicleAuctRepository.findById(auctionId);
    if(!vehicleAuction.isPresent()){
      throw new AuctionException("vehicleAuction is invalid");
    }
    Vehicle vehicle = vehicleAuction.get().getAucVehicle();
    Account account = vehicle.getVehicleAccount().getAccount();
    if(account.getId() == accountIdBidder){
      throw new AuctionException("Auction creater cant be Account Bidder");
    }

    VehicleAuctionHistory lastValue = vehicleAuctHistRepository.getVehicleAuctionHistoryByVehAuc_IdOrderByAuctionPriceDesc(vehicle.getId());

    if(lastValue.getAuctionPrice() >= auctionBid.getPrice()) {
      throw new AuctionException("Auction Bid price is lower than current bid price");
    }
    ReentrantLock lock = new ReentrantLock();
    lock.lock();
    try{
      this.vehicleAuctHistRepository.save(lastValue.toBuilder()
          .acc(account)
          .auctionPrice(auctionBid.getPrice())
          .build());
    }finally {
      lock.unlock();
    }

    return vehicleAuction.get();
  }


  @Scheduled(fixedDelay = 60000)
  public void finishAuction(){
    Timestamp current = new Timestamp(System.currentTimeMillis());
    logger.info("running batch job to clean up all finished auction before ${current}");
    List<VehicleAuction> auctionToClose = vehicleAuctRepository.findAllByAuctionEndTimeBefore(current);
    logger.info("No of auction to update ${auctionToClose.size()}");

    auctionToClose.forEach(auction ->
          vehicleAuctRepository.save(auction.toBuilder()
          .status(AuctionStatus.COMPLETED).build()));

    logger.info("batch job completed");
  }

  public Flux<List<VehicleAuctionHistory>> getVehicleAuctionHistory(int accountId, int vehicleId) throws AuctionException{
    Optional<Account> account = accountRepository.findById(accountId);
    Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);

    if(!account.isPresent() || !vehicle.isPresent()){
      throw new AuctionException("no Valid Account or Vehicle Present");
    }
    Optional<VehicleAuction> vehicleAuction = vehicleAuctRepository.findByAucVehicle_Id(vehicleId);

    if(!vehicleAuction.isPresent()){
      throw new AuctionException("no Valid Auction Present");
    }

    return getAuctionHistory(vehicleAuction.get().getVehicleAuctionHistories());

  }

  public Flux<List<VehicleAuctionHistory>> getAuctionHistory(List<VehicleAuctionHistory> auctions){
    Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
    interval.subscribe((i)-> auctions.forEach(auction -> getAuctionHistory(auction.getVehAuc())));
    Flux<List<VehicleAuctionHistory>> transactionFlux = Flux.fromStream(Stream.generate(() -> auctions));

    return Flux.zip(interval, transactionFlux).map(Tuple2::getT2);

  }

  private VehicleAuctionHistory getAuctionHistory(VehicleAuction auction){
    return vehicleAuctHistRepository.getVehicleAuctionHistoryByVehAuc_Id(auction.getAucVehicle().getId());
  }
}
