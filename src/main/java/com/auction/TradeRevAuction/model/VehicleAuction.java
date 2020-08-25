package com.auction.TradeRevAuction.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@Entity(name ="vehicle_auction")
public class VehicleAuction extends BaseEnity{

  @Id
  @GeneratedValue
  @Column(name = "id", updatable = false, nullable = false)
  private int id;

  @Column(name = "account_id")
  private int accountId;

  @Column(name = "vehicle_id")
  private int vehicleId;

  @Column(name = "base_price")
  private int basePrice;

  @Column(name = "final_price")
  private int finalPrice;

  @Column(name = "status")
  private AuctionStatus status;

  @Column(name = "auction_duration_hr")
  private int auctionDurationHrs;

  @Column(name = "auction_start_time")
  private Timestamp auctionStartTime;

  @Column(name = "auction_end_time")
  private Timestamp auctionEndTime;
}
