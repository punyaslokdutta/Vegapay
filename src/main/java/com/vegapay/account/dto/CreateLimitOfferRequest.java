package com.vegapay.account.dto;

import com.vegapay.account.common.LimitType;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;

public class CreateLimitOfferRequest {
    public int accountId;
    public LimitType limitType;
    public double newLimit;
    public LocalDateTime offerActivationTime;
    public LocalDateTime offerExpiryTime;

    public LocalDateTime getOfferExpiryTime() {
        return this.offerExpiryTime;
    }

    public LocalDateTime getOfferActivationTime() {
        return this.offerActivationTime;
    }
}
