package com.auction.TradeRevAuction.repository;

import com.auction.TradeRevAuction.model.VehicleAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface VehicleAccountRepository extends JpaRepository<VehicleAccount, Integer> {

  List<VehicleAccount> findByAccountId(int accountId);
  VehicleAccount findByVehicleId(int vehicleId);

}
