package com.vegapay.account.service;



import com.vegapay.account.common.LimitType;
import com.vegapay.account.common.OfferStatus;
import com.vegapay.account.dto.CreateLimitOfferRequest;
import com.vegapay.account.models.Account;
import com.vegapay.account.models.LimitOffer;
import com.vegapay.account.repository.AccountRepository;
import com.vegapay.account.repository.LimitOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LimitOfferService {

    private final LimitOfferRepository limitOfferRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public LimitOfferService(LimitOfferRepository limitOfferRepository, AccountRepository accountRepository) {
        this.limitOfferRepository = limitOfferRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public LimitOffer createLimitOffer(CreateLimitOfferRequest createLimitOffer) {
        Account account = accountRepository.findByAccountId(createLimitOffer.accountId);
        if (account == null ) {
            throw new IllegalArgumentException("Account not found");
        }
        if (createLimitOffer.getOfferExpiryTime().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Offer expiry time must be in the future");
        }

        if (createLimitOffer.getOfferExpiryTime().isBefore(createLimitOffer.getOfferActivationTime())) {
            throw new IllegalArgumentException("Offer expiry time must be after offer activation time");
        }

        if (createLimitOffer.limitType == LimitType.ACCOUNT_LIMIT && createLimitOffer.newLimit <= account.getAccountLimit()) {
            throw new IllegalArgumentException("New account limit must be greater than current account limit");
        } else if (createLimitOffer.limitType == LimitType.PER_TRANSACTION_LIMIT && createLimitOffer.newLimit <= account.getPerTransactionLimit()) {
            throw new IllegalArgumentException("New per transaction limit must be greater than current per transaction limit");
        }

        LimitOffer limitOffer = new LimitOffer();
        limitOffer.setAccountId(createLimitOffer.accountId);
        limitOffer.setLimitType(createLimitOffer.limitType);
        limitOffer.setNewLimit(createLimitOffer.newLimit);
        limitOffer.setOfferActivationTime(createLimitOffer.offerActivationTime);
        limitOffer.setOfferExpiryTime(createLimitOffer.offerExpiryTime);
        limitOffer.setStatus(OfferStatus.PENDING);
        return limitOfferRepository.save(limitOffer);
    }

    @Transactional
    public LimitOffer updateLimitOfferStatus(int limitOfferId, OfferStatus newStatus) {
        LimitOffer limitOffer = limitOfferRepository.findByLimitOfferId(limitOfferId);
        if(limitOffer == null)
        {
            throw new IllegalArgumentException("LimitOffer not found");
        }
        Account account = accountRepository.findByAccountId(limitOffer.getAccountId());
        if (account == null ) {
            throw new IllegalArgumentException("Account not found");
        }
        if (newStatus == OfferStatus.ACCEPTED) {
                double newAccountLimit = limitOffer.getLimitType() == LimitType.ACCOUNT_LIMIT ? limitOffer.getNewLimit() : account.getAccountLimit();
                double newPerTransactionLimit = limitOffer.getLimitType() == LimitType.PER_TRANSACTION_LIMIT ? limitOffer.getNewLimit() : account.getPerTransactionLimit();
                account.setLastAccountLimit(account.getAccountLimit());
                account.setLastPerTransactionLimit(account.getPerTransactionLimit());
                account.setAccountLimit(newAccountLimit);
                account.setPerTransactionLimit(newPerTransactionLimit);
                accountRepository.save(account);
                limitOffer.setStatus(OfferStatus.ACCEPTED);
                return limitOfferRepository.save(limitOffer);

        }
        else if(newStatus == OfferStatus.REJECTED){
            limitOffer.setStatus(OfferStatus.REJECTED);
            return limitOfferRepository.save(limitOffer);
        }
        return null;
    }


    public List<LimitOffer> getActiveLimitOffersForAccount(int accountId) {
        Account account = accountRepository.findByAccountId(accountId);
        if (account == null ) {
            throw new IllegalArgumentException("Account not found");
        }
        List<LimitOffer> limitOfferForAccount = limitOfferRepository.findActiveLimitOffers(accountId, LocalDateTime.now());
        return limitOfferForAccount;
    }
}

