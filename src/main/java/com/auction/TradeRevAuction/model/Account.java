package com.auction.TradeRevAuction.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@ToString
@Builder(toBuilder = true)
@Entity(name ="account")
public class Account extends BaseEnity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, nullable = false)
  private int id;

  @Column(name = "name")
  private String name;

  @Enumerated(EnumType.STRING)
  @Column(name = "type")
  private AccountType accountType;

  @Column(name = "email")
  private String email;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "description")
  private String description;

  @Column(name = "email_verified")
  private boolean emailVerified;

  @Column(name = "deleted")
  private boolean deleted;

  @OneToMany(fetch = FetchType.LAZY,
      cascade =  CascadeType.ALL,
      mappedBy = "account",orphanRemoval = true)
  @JsonIgnore
  private List<VehicleAccount> vehicles;

  @OneToMany(fetch = FetchType.LAZY,
      cascade =  CascadeType.ALL,
      mappedBy = "accountVal",orphanRemoval = true)
  @JsonIgnore
  private List<VehicleAuction> vehicleAuctions;

  @OneToMany(fetch = FetchType.LAZY,
      cascade =  CascadeType.ALL,
      mappedBy = "acc",orphanRemoval = true)
  @JsonIgnore
  private List<VehicleAuctionHistory> vehicleAuctionHistories;

}
