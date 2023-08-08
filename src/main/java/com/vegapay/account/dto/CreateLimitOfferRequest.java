package com.vegapay.account.dto;

import com.vegapay.account.common.LimitType;

import java.time.LocalDateTime;

public class CreateLimitOfferRequest {
    private Long accountId;
    public LimitType limitType;
    public double newLimit;
    public LocalDateTime offerActivationTime;
    public LocalDateTime offerExpiryTime;
}
