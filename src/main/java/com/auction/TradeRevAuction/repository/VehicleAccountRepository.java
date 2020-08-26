package com.auction.TradeRevAuction.repository;

import com.auction.TradeRevAuction.model.VehicleAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleAccountRepository extends JpaRepository<VehicleAccount, Integer> {

  List<VehicleAccount> findByAccountId(int accountId);

  VehicleAccount findByVehicleId(int vehicleId);

}
