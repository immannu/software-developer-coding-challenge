package com.auction.TradeRevAuction.service;

import com.auction.TradeRevAuction.Validator.VehicleValidatorServiceImpl;
import com.auction.TradeRevAuction.model.Account;
import com.auction.TradeRevAuction.model.Vehicle;
import com.auction.TradeRevAuction.model.VehicleAccount;
import com.auction.TradeRevAuction.repository.AccountRepository;
import com.auction.TradeRevAuction.repository.VehicleAccountRepository;
import com.auction.TradeRevAuction.repository.VehicleRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService{

  private final AccountRepository accountRepositoryRepository;
  private final VehicleRepository vehicleRepository;
  private final VehicleAccountRepository vehicleAccRepository;


  public VehicleServiceImpl(AccountRepository accountRepositoryRepository, VehicleRepository vehicleRepository,
                            VehicleAccountRepository vehicleAccRepository) {
    this.accountRepositoryRepository = accountRepositoryRepository;
    this.vehicleRepository = vehicleRepository;
    this.vehicleAccRepository = vehicleAccRepository;
  }

  @Override
  public Vehicle addVehicle(int accountId, Vehicle vehicle) throws NotFoundException {
    Optional<Account> value = this.accountRepositoryRepository.findById(accountId);

    if(!value.isPresent())
      throw new NotFoundException("Account with ${id} doesnt exist");

    Vehicle valuedVehicle = Vehicle.builder()
                                    .year(vehicle.getYear())
                                    .make(vehicle.getMake())
                                    .model(vehicle.getModel())
                                    .trim(vehicle.getTrim())
                                    .vin(vehicle.getVin())
                                    .vehicleValue(VehicleValidatorServiceImpl.getVehicleValueByVin(vehicle.getVin()))
                                    .build();

    Vehicle v = this.vehicleRepository.save(valuedVehicle);
    VehicleAccount vAcc = this.vehicleAccRepository.save(VehicleAccount.builder()
                          .account(value.get())
                          .vehicle(v).build());

    return valuedVehicle;
  }

  @Override
  public List<Vehicle> getAllVehicle(int accountId) throws NotFoundException {
    Optional<Account> value = this.accountRepositoryRepository.findById(accountId);

    if(!value.isPresent())
      throw new NotFoundException("Account with ${id} doesnt exist");

    return this.vehicleRepository.getVehiclesByAccountId(accountId);
  }

  @Override
  public Vehicle getVehicle(int accountId, int vehicleId) throws NotFoundException {
    Optional<Account> value = this.accountRepositoryRepository.findById(accountId);

    if(!value.isPresent())
      throw new NotFoundException("Account with ${id} doesnt exist");
    VehicleAccount vAcc = this.vehicleAccRepository.findByVehicleId(vehicleId);

    if(accountId!= vAcc.getAccount().getId()){
      throw new IllegalArgumentException("Vehicle with ${vehicleId} doest belong to Account with ${accountId}");
    }

    return this.vehicleRepository.getOne(vehicleId);
  }
}
