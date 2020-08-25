package com.auction.TradeRevAuction.repository;

import com.auction.TradeRevAuction.model.Vehicle;
import com.auction.TradeRevAuction.model.VehicleAuction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleAuctionRepository extends CrudRepository<VehicleAuction, Integer> {
}
