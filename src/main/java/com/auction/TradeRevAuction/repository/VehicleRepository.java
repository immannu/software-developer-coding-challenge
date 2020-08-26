package com.auction.TradeRevAuction.repository;

import com.auction.TradeRevAuction.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

  @Query("SELECT v FROM Vehicle v inner join vehicle_account va on v.id = va.vehicle_id WHERE va.account_id= :accId")
  public List<Vehicle> getVehiclesByAccountId(@Param("accId") int accId);

}
