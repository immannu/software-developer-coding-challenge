package com.auction.TradeRevAuction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TradeRevAuctionApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradeRevAuctionApplication.class, args);
	}

}
