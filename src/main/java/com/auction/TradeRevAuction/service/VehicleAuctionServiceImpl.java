package com.auction.TradeRevAuction.service;

import com.auction.TradeRevAuction.Exception.AuctionException;
import com.auction.TradeRevAuction.Validator.VehicleValidatorServiceImpl;
import com.auction.TradeRevAuction.model.AuctionStatus;
import com.auction.TradeRevAuction.model.Vehicle;
import com.auction.TradeRevAuction.model.VehicleAuction;
import com.auction.TradeRevAuction.repository.AccountRepository;
import com.auction.TradeRevAuction.repository.VehicleAuctionHistoryRepository;
import com.auction.TradeRevAuction.repository.VehicleAuctionRepository;
import com.auction.TradeRevAuction.repository.VehicleRepository;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class VehicleAuctionServiceImpl implements VehicleAuctionService {

  static Logger logger = LoggerFactory.getLogger(VehicleAuctionServiceImpl.class);


  private final AccountRepository accountRepository;
  private final VehicleRepository vehicleRepository;
  private final VehicleAuctionRepository vehicleAuctRepository;
  private final VehicleAuctionHistoryRepository vehicleAuctHistRepository;

  public VehicleAuctionServiceImpl(AccountRepository accountRepositoryRepository, VehicleRepository vehicleRepository, VehicleAuctionRepository vehicleAuctRepository, VehicleAuctionHistoryRepository vehicleAuctHistRepository) {
    this.accountRepository = accountRepositoryRepository;
    this.vehicleRepository = vehicleRepository;
    this.vehicleAuctRepository = vehicleAuctRepository;
    this.vehicleAuctHistRepository = vehicleAuctHistRepository;
  }

  @Override
  public VehicleAuction createAuction(int accountId, Vehicle vehicle, VehicleAuction auction) throws NotFoundException, AuctionException {
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
    return auction.toBuilder().aucVehicle(vehicle).status(AuctionStatus.NOT_STARTED).build();
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

    return auction.toBuilder().status(AuctionStatus.IN_PROGRESS).auctionEndTime(endTime).build();
  }

  @Override
  public VehicleAuction updateAuction(int accountId, Vehicle vehicle, int accountIdBidder, VehicleAuction auction) throws NotFoundException {
    return null;
  }

  @Override
  public VehicleAuction finishAuction(int accountId, Vehicle vehicle, VehicleAuction auction) throws NotFoundException {
    return null;
  }
}
