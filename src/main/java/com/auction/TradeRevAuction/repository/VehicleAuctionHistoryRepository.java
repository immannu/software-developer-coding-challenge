package com.auction.TradeRevAuction.repository;

import com.auction.TradeRevAuction.model.VehicleAuctionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface VehicleAuctionHistoryRepository extends JpaRepository<VehicleAuctionHistory, Integer> {
  VehicleAuctionHistory getVehicleAuctionHistoryByVehAuc_Id(int vehicleId);
  VehicleAuctionHistory getVehicleAuctionHistoryByVehAuc_IdOrderByAuctionPriceDesc(int vehicleId);

  @Query(value = "Select vah.* from vehicle_auction_history  vah inner join vehicle_auction va on vah.vehicle_auction_id= va.id  where vah.vehicle_id =:vehicleId order by vah.auction_price desc LIMIT 1", nativeQuery = true)
  VehicleAuctionHistory getLastVehicleAuction(@Param("vehicleId") int vehicleId);

}
