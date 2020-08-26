package com.auction.TradeRevAuction.controller;

import com.auction.TradeRevAuction.model.Account;
import com.auction.TradeRevAuction.model.Vehicle;
import com.auction.TradeRevAuction.service.VehicleService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import javax.validation.Valid;

@RestController
public class VehicleController {

  @Autowired
  private VehicleService vehicleService;

  @GetMapping("/accounts/{id}")
  public List<Account> getAllVehicles() {

    List<Vehicle> vehicles = vehicleService.

    return accounts;
  }

  @GetMapping("/accounts/{id}")
  public Account getAccount(@PathVariable(name = "id") int id) throws NotFoundException {

    return accountService.get(id);
  }

  @PostMapping ("/accounts")
  public Account createAccount(@RequestBody @Valid Account account) {

    return accountService.create(account);
  }

  @PutMapping("/accounts/{id}")
  public Account updateAccount(@PathVariable(name = "id") int id,@RequestBody @Valid Account account) throws NotFoundException{

    return accountService.update(account);
  }

  @DeleteMapping("/accounts/{id}")
  public void deleteAccount(@PathVariable(name = "id") int id) throws NotFoundException{

    accountService.delete(id);
  }
}
