package com.vegapay.account.models;

import java.time.LocalDateTime;

public class Account {
    private Long accountId;
    private Long customerId;
    private double accountLimit;
    private double perTransactionLimit;
    private double lastAccountLimit;
    private double lastPerTransactionLimit;
    private LocalDateTime accountLimitUpdateTime;
    private LocalDateTime perTransactionLimitUpdateTime;
}