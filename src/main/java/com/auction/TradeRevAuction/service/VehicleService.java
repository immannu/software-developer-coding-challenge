package com.auction.TradeRevAuction.service;

import com.auction.TradeRevAuction.model.Vehicle;
import javassist.NotFoundException;

import java.util.List;

public interface VehicleService {

  Vehicle addVehicle(int accountId, Vehicle vehicle ) throws NotFoundException;
  List<Vehicle> getAllVehicle(int accountId ) throws NotFoundException;
  Vehicle getVehicle(int accountId, int vehicleId ) throws NotFoundException;



}
