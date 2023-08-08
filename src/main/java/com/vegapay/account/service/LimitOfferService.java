package com.vegapay.account.service;



import com.vegapay.account.common.OfferStatus;
import com.vegapay.account.models.LimitOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LimitOfferService {

    private final LimitOfferRepository limitOfferRepository;

    @Autowired
    public LimitOfferService(LimitOfferRepository limitOfferRepository) {
        this.limitOfferRepository = limitOfferRepository;
    }

    public LimitOffer createLimitOffer(LimitOffer limitOffer) {
        // Implement logic to create a new limit offer
        return limitOfferRepository.save(limitOffer);
    }

    public List<LimitOffer> listLimitOffersForUser(Long accountId) {
        // fetch active limit offers for the given user
        // use limitOfferRepository.findByAccountIdAndStatus(accountId, OfferStatus.PENDING);
        return null; // Replace with actual implementation
    }

    public LimitOffer updateLimitOfferStatus(Long limitOfferId, OfferStatus newStatus) {
        LimitOffer limitOffer = limitOfferRepository.findById(limitOfferId).orElse(null);
        if (limitOffer != null) {
            limitOffer.setStatus(newStatus);
            // Implement logic to update the limit offer status
            return limitOfferRepository.save(limitOffer);
        }
        return null;
    }
}
