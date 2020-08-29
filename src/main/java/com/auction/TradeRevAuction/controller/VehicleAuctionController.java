package com.auction.TradeRevAuction.controller;

import com.auction.TradeRevAuction.Exception.AuctionException;
import com.auction.TradeRevAuction.model.AuctionBid;
import com.auction.TradeRevAuction.model.Vehicle;
import com.auction.TradeRevAuction.model.VehicleAuction;
import com.auction.TradeRevAuction.model.VehicleAuctionHistory;
import com.auction.TradeRevAuction.service.VehicleAuctionServiceImpl;
import com.auction.TradeRevAuction.service.VehicleService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;
import javax.validation.Valid;

@RestController
public class VehicleAuctionController {

  @Autowired
  private VehicleAuctionServiceImpl vehicleAuctionService;

  @Autowired
  private VehicleService vehicleService;

  @PostMapping("/accounts/{accId}/vehicle/{vId}/auction")
  public VehicleAuction createAuction(@PathVariable(name = "accId") int accId, @PathVariable(name = "vId") int vId, @RequestBody @Valid VehicleAuction auction) throws NotFoundException, AuctionException {
    return vehicleAuctionService.createAuction(accId, vId, auction);
  }

  @PutMapping("/accounts/{accId}/auction/{aucId}")
  public VehicleAuction startAuction(@PathVariable(name = "accId") int accId, @PathVariable(name = "aucId") int aucId) throws NotFoundException, AuctionException {

    return vehicleAuctionService.startAuction(accId, aucId);
  }

  @PutMapping("/accounts/{accId}/auction/{aucId}/bid")
  public VehicleAuction updateAuction(@PathVariable(name = "accId") int accId, @PathVariable(name = "aucId") int aucId, @RequestBody @Valid AuctionBid bid ) throws NotFoundException, AuctionException {

    return vehicleAuctionService.updateAuction(accId,aucId,bid);
  }

  @GetMapping(value = "/accounts/{accId}/vehicle/{vId}/auction",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<List<VehicleAuctionHistory>> getVehicleAuction(@PathVariable(name = "accId") int accId, @PathVariable(name = "vId") int vId) throws AuctionException {
    return vehicleAuctionService.getVehicleAuctionHistory(accId,vId);
  }
}
