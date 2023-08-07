package com.vegapay.account.models;

import com.vegapay.account.common.LimitType;
import com.vegapay.account.common.OfferStatus;

import java.time.LocalDateTime;

public class LimitOffer {
    private Long limitOfferId;
    private Long accountId;
    private LimitType limitType;
    private double newLimit;
    private LocalDateTime offerActivationTime;
    private LocalDateTime offerExpiryTime;
    private OfferStatus status;
}
