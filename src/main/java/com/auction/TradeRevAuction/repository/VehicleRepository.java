package com.auction.TradeRevAuction.repository;

import com.auction.TradeRevAuction.model.Vehicle;
import com.auction.TradeRevAuction.model.VehicleAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

  @Query(value = "Select v.* from vehicle  v inner join vehicle_account va on v.id = va.vehicle_id  where va.account_id =:accId", nativeQuery = true)
   List<Vehicle> getVehiclesByAccountId(@Param("accId") int accId);

}
