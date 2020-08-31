package com.auction.TradeRevAuction.repository;

import com.auction.TradeRevAuction.model.VehicleAuction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface VehicleAuctionRepository extends JpaRepository<VehicleAuction, Integer> {
  Optional<VehicleAuction> findByAucVehicle_Id(int vehicleId);
  List<VehicleAuction> findAllByAuctionEndTimeBefore(Timestamp timestamp);

}
