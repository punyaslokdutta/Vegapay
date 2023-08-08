package com.vegapay.account.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;

    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false)
    private double accountLimit;

    @Column(nullable = false)
    private double perTransactionLimit;

    @Column(nullable = true)
    private double lastAccountLimit;

    @Column(nullable = true)
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

    public double getAccountLimit() {
        return accountLimit;
    }

    public double getPerTransactionLimit() {
        return perTransactionLimit;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setAccountLimit(Double  accountLimit) {
        this.accountLimit = accountLimit;
    }

    public void setPerTransactionLimit(Double perTransactionLimit) {
        this.perTransactionLimit = perTransactionLimit;
    }

    public void setLastAccountLimit(Double accountLimit) {
        this.lastAccountLimit = lastAccountLimit;
    }

    public void setLastPerTransactionLimit(Double lastPerTransactionLimit) {
        this.lastPerTransactionLimit = lastPerTransactionLimit;
    }

    public void setAccountLimitUpdateTime(LocalDateTime now) {
        this.accountLimitUpdateTime = now;
    }

    public void setPerTransactionLimitUpdateTime(LocalDateTime now) {
        this.perTransactionLimitUpdateTime = now;
    }
}
