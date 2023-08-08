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
    private LimitType limitType;

    @Column(nullable = false)
    private double newLimit;

    @Column(nullable = false)
    private LocalDateTime offerActivationTime;

    @Column(nullable = false)
    private LocalDateTime offerExpiryTime;

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
}
