package com.auction.TradeRevAuction;

import com.auction.TradeRevAuction.model.Account;
import com.auction.TradeRevAuction.model.AccountType;

public class TestUtils {

  public static Account createAccount(){

    return Account.builder()
                  .name("Test").accountType(AccountType.DEALER)
                  .description("Test").email("test@test.com")
                  .phoneNumber("555-5555-9999")
                  .build();

  }
}
