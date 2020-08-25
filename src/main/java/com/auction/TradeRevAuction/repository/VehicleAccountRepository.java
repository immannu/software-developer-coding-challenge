package com.auction.TradeRevAuction.repository;

import com.auction.TradeRevAuction.model.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleAccountRepository extends CrudRepository<Vehicle, Integer> {
}
