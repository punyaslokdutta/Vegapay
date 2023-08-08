package com.vegapay.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Account account) {
        // create account
        return accountRepository.save(account);
    }

    public Account getAccount(Long accountId) {
        //
        return accountRepository.findById(accountId).orElse(null);
    }
}
