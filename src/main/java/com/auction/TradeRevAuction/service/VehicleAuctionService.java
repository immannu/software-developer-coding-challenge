package com.auction.TradeRevAuction.service;

import com.auction.TradeRevAuction.Exception.AuctionException;
import com.auction.TradeRevAuction.model.Vehicle;
import com.auction.TradeRevAuction.model.VehicleAuction;
import javassist.NotFoundException;

import java.util.List;

public interface VehicleAuctionService {

  VehicleAuction createAuction(int accountId, Vehicle vehicle, VehicleAuction auction) throws NotFoundException, AuctionException;
  VehicleAuction startAuction(int accountId, VehicleAuction auction) throws NotFoundException, AuctionException;
  VehicleAuction updateAuction(int accountId, Vehicle vehicle, int accountIdBidder, VehicleAuction auction) throws NotFoundException;
  VehicleAuction finishAuction(int accountId, Vehicle vehicle, VehicleAuction auction) throws NotFoundException;
}
