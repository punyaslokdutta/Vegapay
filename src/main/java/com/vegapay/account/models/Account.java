package com.vegapay.account.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false)
    private double accountLimit;

    @Column(nullable = false)
    private double perTransactionLimit;

    @Column(nullable = false)
    private double lastAccountLimit;

    @Column(nullable = false)
    private double lastPerTransactionLimit;

    @Column(nullable = false)
    private LocalDateTime accountLimitUpdateTime;

    @Column(nullable = false)
    private LocalDateTime perTransactionLimitUpdateTime;
    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", customerId=" + customerId +
                ", accountLimit=" + accountLimit +
                ", perTransactionLimit=" + perTransactionLimit +
                ", lastAccountLimit=" + lastAccountLimit +
                ", lastPerTransactionLimit=" + lastPerTransactionLimit +
                ", accountLimitUpdateTime=" + accountLimitUpdateTime +
                ", perTransactionLimitUpdateTime=" + perTransactionLimitUpdateTime +
                '}';
    }
}
