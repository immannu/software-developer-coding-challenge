package com.auction.TradeRevAuction.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "vehicleAccount")
@Builder(toBuilder = true)
@Entity(name ="vehicle")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Vehicle extends BaseEnity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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

  @Column(name = "vehicle_value")
  private int vehicleValue;

  @OneToOne(fetch = FetchType.EAGER,
      cascade =  CascadeType.ALL,
      mappedBy = "vehicle")
  @JsonIgnore
  private VehicleAccount vehicleAccount;

  @OneToOne(fetch = FetchType.LAZY,
      cascade =  CascadeType.ALL,
      mappedBy = "aucVehicle")
  @JsonIgnore
  private VehicleAuction vehicleAuction;

}
