package com.auction.TradeRevAuction.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude={"vehAuc","veh"})
@Builder(toBuilder = true)
@Entity(name ="vehicle_auction_history")
public class VehicleAuctionHistory extends BaseEnity{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, nullable = false)
  private int id;

  @Column(name = "base_price")
  private int basePrice;

  @Column(name = "auction_price")
  private int auctionPrice;

  @OneToOne(fetch = FetchType.EAGER, optional = true)
  @JoinColumn(name = "vehicle_id", nullable = false)
  @JsonIgnore
  private Vehicle veh;

  @ManyToOne(fetch = FetchType.EAGER, optional = true)
  @JoinColumn(name = "vehicle_auction_id", nullable = false)
  @JsonIgnore
  private VehicleAuction vehAuc;
}
