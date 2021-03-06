package com.auction.TradeRevAuction.service;

import com.auction.TradeRevAuction.Exception.AuctionException;
import com.auction.TradeRevAuction.model.AuctionBid;
import com.auction.TradeRevAuction.model.VehicleAuction;
import javassist.NotFoundException;

public interface VehicleAuctionService {

  VehicleAuction createAuction(int accountId, int vehicleId, VehicleAuction auction) throws NotFoundException, AuctionException;
  VehicleAuction startAuction(int accountId, int auctionId) throws NotFoundException, AuctionException;
  VehicleAuction updateAuction(int accountIdBidder, int auctionId, AuctionBid auctionBid) throws AuctionException;
}
