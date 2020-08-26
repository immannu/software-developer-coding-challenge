package com.auction.TradeRevAuction.Validator;

import com.auction.TradeRevAuction.model.Vehicle;


public interface VehicleValidatorService {

  void validateByVin(String vinNo) throws Exception;
  void validateByYearMakeModel(Vehicle vehicle);
  int getVehicleValueByVin(String vinNo);
  int getVehicleValue(Vehicle vehicle);

}