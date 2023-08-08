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
import java.util.Optional;

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
    public LimitOffer createLimitOffer(CreateLimitOfferRequest createlimitOffer) {
        Account account = accountRepository.findByAccountId(createlimitOffer.accountId);
        if (account == null ) {
            throw new IllegalArgumentException("Account not found");
        }

        if (createlimitOffer.limitType == LimitType.ACCOUNT_LIMIT && createlimitOffer.newLimit <= account.getAccountLimit()) {
            throw new IllegalArgumentException("New account limit must be greater than current account limit");
        } else if (createlimitOffer.limitType == LimitType.PER_TRANSACTION_LIMIT && createlimitOffer.newLimit <= account.getPerTransactionLimit()) {
            throw new IllegalArgumentException("New per transaction limit must be greater than current per transaction limit");
        }
        LimitOffer limitOffer = new LimitOffer();
        limitOffer.setAccountId(createlimitOffer.accountId);
        limitOffer.setLimitType(createlimitOffer.limitType);
        limitOffer.setNewLimit(createlimitOffer.newLimit);
        limitOffer.setOfferActivationTime(createlimitOffer.offerActivationTime);
        limitOffer.setOfferExpiryTime(createlimitOffer.offerExpiryTime);
        limitOffer.setStatus(OfferStatus.PENDING);
        return limitOfferRepository.save(limitOffer);
    }

    @Transactional
    public LimitOffer updateLimitOfferStatus(int limitOfferId, OfferStatus newStatus) {
        LimitOffer limitOffer = limitOfferRepository.findByLimitOfferId(limitOfferId);

        if (limitOffer != null && newStatus == OfferStatus.ACCEPTED) {
            Account account = accountRepository.findByAccountId(limitOffer.getAccountId());

            if (account != null) {
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
        }
        return null;
    }


    public List<LimitOffer> getActiveLimitOffersForAccount(Long accountId) {
        List<LimitOffer> limitOfferForAccount = limitOfferRepository.findActiveLimitOffers(accountId, LocalDateTime.now());
        return limitOfferForAccount;
    }
}

