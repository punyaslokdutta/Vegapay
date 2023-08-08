package com.vegapay.account.controller;

import com.vegapay.account.common.OfferStatus;
import com.vegapay.account.dto.CreateLimitOfferRequest;
import com.vegapay.account.models.LimitOffer;
import com.vegapay.account.service.LimitOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/limit-offers")
public class LimitOfferController {

    private final LimitOfferService limitOfferService;

    @Autowired
    public LimitOfferController(LimitOfferService limitOfferService) {
        this.limitOfferService = limitOfferService;
    }

    @PostMapping
    public ResponseEntity<?> createLimitOffer(@RequestBody CreateLimitOfferRequest limitOffer) {
        try{
            LimitOffer createdLimitOffer = limitOfferService.createLimitOffer(limitOffer);
            return ResponseEntity.ok(createdLimitOffer);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }

    }
    @GetMapping("/active/{accountId}")
    public ResponseEntity<?> getActiveLimitOffersForAccount(@PathVariable int accountId) {
        try{
            List<LimitOffer> activeLimitOffers = limitOfferService.getActiveLimitOffersForAccount(accountId);
            return ResponseEntity.ok(activeLimitOffers);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }

    }

    @PutMapping("/{limitOfferId}/status")
    public ResponseEntity<?> updateLimitOfferStatus(
            @PathVariable int  limitOfferId,
            @RequestBody Map<String, String> requestBody
    ) {
        String newStatusStr = requestBody.get("status");
        OfferStatus newStatus = OfferStatus.valueOf(newStatusStr);
        try{
            LimitOffer updatedLimitOffer = limitOfferService.updateLimitOfferStatus(limitOfferId, newStatus);
            return ResponseEntity.ok(updatedLimitOffer);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }

    }
}
