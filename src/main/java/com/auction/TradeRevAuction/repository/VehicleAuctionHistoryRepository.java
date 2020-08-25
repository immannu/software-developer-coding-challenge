package com.auction.TradeRevAuction.repository;

import com.auction.TradeRevAuction.model.VehicleAuctionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleAuctionHistoryRepository extends JpaRepository<VehicleAuctionHistory, Integer> {
}
