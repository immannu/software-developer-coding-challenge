package com.auction.TradeRevAuction.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@Entity(name ="account")
public class VehicleAccount extends BaseEnity{

  @Id
  @GeneratedValue
  @Column(name = "id", updatable = false, nullable = false)
  private int id;

  @Column(name = "account_id")
  private int accountId;

  @Column(name = "vehicle_id")
  private int vehicleId;

}
