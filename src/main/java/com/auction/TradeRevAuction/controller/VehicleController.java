package com.auction.TradeRevAuction.controller;

import com.auction.TradeRevAuction.model.Vehicle;
import com.auction.TradeRevAuction.service.VehicleService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import javax.validation.Valid;

@RestController
public class VehicleController {

  @Autowired
  private VehicleService vehicleService;

  @GetMapping("/accounts/{acId}/vehicle")
  public List<Vehicle> getAllVehicles(@PathVariable(name = "acId") int acId) throws NotFoundException {

    List<Vehicle> vehicles = vehicleService.getAllVehicle(acId);

    return vehicles;
  }

  @GetMapping("/accounts/{acId}/vehicle/{vId}")
  public Vehicle getVehicle(@PathVariable(name = "acId") int acId, @PathVariable(name = "vId") int vId) throws NotFoundException {

    Vehicle vehicle = vehicleService.getVehicle(acId, vId);

    return vehicle;
  }

  @PostMapping ("/accounts/{acId}/vehicle")
  public Vehicle addVehicle(@PathVariable(name = "acId") int acId, @RequestBody @Valid Vehicle vehicle) throws NotFoundException {

    return vehicleService.addVehicle(acId, vehicle);
  }

}
