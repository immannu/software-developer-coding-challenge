package com.auction.TradeRevAuction.repository;

import com.auction.TradeRevAuction.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {

}
