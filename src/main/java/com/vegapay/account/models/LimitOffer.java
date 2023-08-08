package com.vegapay.account.models;

import com.vegapay.account.common.LimitType;
import com.vegapay.account.common.OfferStatus;
import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
@Table(name = "limit_offer")
public class LimitOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long limitOfferId;

    @Column(nullable = false)
    private Long accountId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public LimitType limitType;

    @Column(nullable = false)
    public double newLimit;

    @Column(nullable = false)
    public LocalDateTime offerActivationTime;

    @Column(nullable = false)
    public LocalDateTime offerExpiryTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OfferStatus status;
    @Override
    public String toString() {
        return "LimitOffer{" +
                "limitOfferId=" + limitOfferId +
                ", accountId=" + accountId +
                ", limitType=" + limitType +
                ", newLimit=" + newLimit +
                ", offerActivationTime=" + offerActivationTime +
                ", offerExpiryTime=" + offerExpiryTime +
                ", status=" + status +
                '}';
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setLimitType(LimitType limitType) {
        this.limitType = limitType;
    }

    public void setNewLimit(double newLimit) {
        this.newLimit = newLimit;
    }

    public void setOfferActivationTime(LocalDateTime offerActivationTime) {
        this.offerActivationTime = offerActivationTime;
    }

    public void setOfferExpiryTime(LocalDateTime offerExpiryTime) {
        this.offerExpiryTime = offerExpiryTime;
    }

    public void setStatus(OfferStatus pending) {
        this.status = pending;
    }

    public Long getAccountId() {
        return this.accountId;
    }

    public LimitType getLimitType() {
        return this.limitType;
    }

    public double getNewLimit() {
        return this.newLimit;
    }
}
