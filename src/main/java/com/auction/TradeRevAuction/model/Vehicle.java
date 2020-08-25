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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@Entity(name ="vehicle")
public class Vehicle extends BaseEnity{

  @Id
  @GeneratedValue
  @Column(name = "id", updatable = false, nullable = false)
  private int id;

  @Column(name = "year")
  private String year;

  @Column(name = "make")
  private String make;

  @Column(name = "model")
  private String model;

  @Column(name = "trim")
  private String trim;

  @Column(name = "vin")
  private String vin;
}
