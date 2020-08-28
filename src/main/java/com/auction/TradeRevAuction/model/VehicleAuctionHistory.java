package com.auction.TradeRevAuction.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@Entity(name ="vehicle_auction_hist0ry")
public class VehicleAuctionHistory extends BaseEnity{

  @Id
  @GeneratedValue
  @Column(name = "id", updatable = false, nullable = false)
  private int id;

//  @Column(name = "account_id")
//  private int accountId;
//
//  @Column(name = "vehicle_id")
//  private int vehicleId;
//
//  @Column(name = "vehicle_auction_id")
//  private int vehicleAuctionId;

  @Column(name = "base_price")
  private int basePrice;

  @Column(name = "auction_price")
  private int auctionPrice;

  @OneToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "account_id", nullable = true)
  @JsonIgnore
  private Account acc;

  @OneToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "vehicle_id", nullable = true)
  @JsonIgnore
  private Vehicle veh;

  @OneToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "vehicle_auction_id", nullable = true)
  @JsonIgnore
  private VehicleAuction vehAuc;
}
