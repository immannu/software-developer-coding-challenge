package com.auction.TradeRevAuction.Validator;

import com.auction.TradeRevAuction.Exception.AuctionException;
import com.auction.TradeRevAuction.model.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.SplittableRandom;

// this will be Service used to validate vehicle title , validation using
// any third party Service Providers

public class VehicleValidatorServiceImpl {

  static Logger logger = LoggerFactory.getLogger(VehicleValidatorServiceImpl.class);
  public static void validateByVin(String vinNo) throws AuctionException{
    logger.info("Validating Vehicle with vin: ${vinNo} ");

    //mock third part service behavior

    if(vinNo.startsWith("STOLEN")){
      throw new AuctionException(" Vehicle with vin: ${vinNo} is stolen");
    }

    logger.info("Finished Vehicle validation with vin: ${vinNo} and no issue found");
  }

  public static void validateByYearMakeModel(Vehicle vehicle) {

    logger.info("Validating Vehicle with Vehicle: ${vehicle} ");

    //call  third part service behavior

    logger.info("Finished Vehicle validation with Vehicle: ${vehicle} and no issue found");
  }

  public static int getVehicleValueByVin(String vinNo) {
    //call 3rd part service provider to get Vehicle valuation
    logger.info("Computing  Value of Vehicle with vin: ${vinNo} ");
    int n = new SplittableRandom().nextInt(7000, 50_001);
    logger.info("Computed  Value of Vehicle with vin: ${vinNo} as ${n}");
    return n;
  }

  public static int getVehicleValue(Vehicle vehicle) {
    //call 3rd part service provider to get Vehicle valuation
    logger.info("Computing  Value of Vehicle with Vehicle: ${vehicle} ");
    int n = new SplittableRandom().nextInt(7000, 50_001);
    logger.info("Computed  Value of Vehicle with Vehicle: ${vehicle} as ${n}");
    return n;
  }
}
