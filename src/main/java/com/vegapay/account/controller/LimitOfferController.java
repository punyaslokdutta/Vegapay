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
    public ResponseEntity<LimitOffer> createLimitOffer(@RequestBody CreateLimitOfferRequest limitOffer) {
        LimitOffer createdLimitOffer = limitOfferService.createLimitOffer(limitOffer);
        return ResponseEntity.ok(createdLimitOffer);
    }
    @GetMapping("/active/{accountId}")
    public ResponseEntity<List<LimitOffer>> getActiveLimitOffersForAccount(@PathVariable Long accountId) {
        List<LimitOffer> activeLimitOffers = limitOfferService.getActiveLimitOffersForAccount(accountId);
        return ResponseEntity.ok(activeLimitOffers);
    }

    @PutMapping("/{limitOfferId}/status")
    public ResponseEntity<LimitOffer> updateLimitOfferStatus(
            @PathVariable int  limitOfferId,
            @RequestBody Map<String, String> requestBody
    ) {
        String newStatusStr = requestBody.get("status");
        OfferStatus newStatus = OfferStatus.valueOf(newStatusStr);
        LimitOffer updatedLimitOffer = limitOfferService.updateLimitOfferStatus(limitOfferId, newStatus);
        if (updatedLimitOffer != null) {
            return ResponseEntity.ok(updatedLimitOffer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
