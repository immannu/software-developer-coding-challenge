package com.auction.TradeRevAuction.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AuctionStatus {
  IN_PROGRESS("IN_PROGRESS"),
  CANCELLED("CANCELLED"),
  COMPLETED("COMPLETED"),
  NOT_STARTED("NOT_STARTED");

  private String value;

  private AuctionStatus(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return this.value;
  }

  public String toString() {
    return String.valueOf(this.value);
  }
}
