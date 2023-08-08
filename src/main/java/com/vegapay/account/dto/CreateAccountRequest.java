package com.vegapay.account.dto;

public class CreateAccountRequest {
    private Long customerId;
    private double accountLimit;
    private double perTransactionLimit;

    public Long getCustomerId() {
        return this.customerId;
    }

    public Double getAccountLimit() {
        return this.accountLimit;
    }

    public Double getPerTransactionLimit() {
        return this.perTransactionLimit;
    }
}