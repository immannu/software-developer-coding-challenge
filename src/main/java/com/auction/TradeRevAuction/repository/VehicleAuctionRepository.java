package com.auction.TradeRevAuction.repository;

import com.auction.TradeRevAuction.model.Vehicle;
import com.auction.TradeRevAuction.model.VehicleAuction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleAuctionRepository extends JpaRepository<VehicleAuction, Integer> {
  Optional<VehicleAuction> findByAucVehicle_Id(int vehicleId);
  List<VehicleAuction> findAllByAuctionEndTimeBefore(Timestamp timestamp);

}
