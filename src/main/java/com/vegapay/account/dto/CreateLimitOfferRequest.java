package com.vegapay.account.dto;

import com.vegapay.account.common.LimitType;

import java.time.LocalDateTime;

public class CreateLimitOfferRequest {
    private Long accountId;
    private LimitType limitType;
    private double newLimit;
    private LocalDateTime offerActivationTime;
    private LocalDateTime offerExpiryTime;
}
