package com.vegapay.account.service;

import com.vegapay.account.dto.CreateAccountRequest;
import com.vegapay.account.models.Account;
import com.vegapay.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(CreateAccountRequest createAccountRequest) {
        Long customerId = createAccountRequest.getCustomerId();
        if (accountRepository.existsByCustomerId(customerId)) {
            throw new IllegalArgumentException("Customer already has an account");
        }

        Account account = new Account();
        account.setCustomerId(createAccountRequest.getCustomerId());
        account.setAccountLimit(createAccountRequest.getAccountLimit());
        account.setPerTransactionLimit(createAccountRequest.getPerTransactionLimit());
        account.setLastAccountLimit(0.0);
        account.setLastPerTransactionLimit(0.0);
        account.setAccountLimitUpdateTime(LocalDateTime.now());
        account.setPerTransactionLimitUpdateTime(LocalDateTime.now());
        return accountRepository.save(account);
    }

    public Account getAccount(int accountId) {
        return accountRepository.findByAccountId(accountId);
    }
}


