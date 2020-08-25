package com.auction.TradeRevAuction.repository;

import com.auction.TradeRevAuction.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

}
