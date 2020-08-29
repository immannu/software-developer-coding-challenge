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
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude={"vehicleAuctionHistories","accountVal","aucVehicle"})
@Builder(toBuilder = true)
@Entity(name ="vehicle_auction")
public class VehicleAuction extends BaseEnity{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, nullable = false)
  private int id;

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

  @OneToOne(fetch = FetchType.EAGER, optional = true)
  @JoinColumn(name = "account_id", nullable = false)
  @JsonIgnore
  private Account accountVal;

  @OneToOne(fetch = FetchType.EAGER, optional = true)
  @JoinColumn(name = "vehicle_id", nullable = false)
  @JsonIgnore
  private Vehicle aucVehicle;

  @OneToMany(fetch = FetchType.EAGER,
      cascade =  CascadeType.ALL,
      mappedBy = "vehAuc")
  @JsonIgnore
  private List<VehicleAuctionHistory> vehicleAuctionHistories;
}
