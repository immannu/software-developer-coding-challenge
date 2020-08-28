package com.auction.TradeRevAuction.controller;

import com.auction.TradeRevAuction.Exception.AuctionException;
import com.auction.TradeRevAuction.model.Account;
import com.auction.TradeRevAuction.model.Vehicle;
import com.auction.TradeRevAuction.model.VehicleAuction;
import com.auction.TradeRevAuction.service.VehicleAuctionServiceImpl;
import com.auction.TradeRevAuction.service.VehicleService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class VehicleAuctionController {

  @Autowired
  private VehicleAuctionServiceImpl vehicleAuctionService;

  @Autowired
  private VehicleService vehicleService;

  @PostMapping("/accounts/{accId}/vehicle/{vId}/auction")
  public VehicleAuction createAuction(@PathVariable(name = "accId") int accId, @PathVariable(name = "vId") int vId, @RequestBody @Valid VehicleAuction auction) throws NotFoundException, AuctionException {
    Vehicle vehicle = vehicleService.getVehicle(accId, vId);

    return vehicleAuctionService.createAuction(accId, vehicle, auction);
  }
}
