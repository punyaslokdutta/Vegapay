package com.vegapay.account.repository;

import com.vegapay.account.models.LimitOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface LimitOfferRepository extends JpaRepository<LimitOffer, Long> {
    @Query("SELECT lo FROM LimitOffer lo WHERE lo.accountId = :accountId AND lo.status = 'PENDING' AND lo.offerActivationTime <= :activeDate AND lo.offerExpiryTime >= :activeDate")
    List<LimitOffer> findActiveLimitOffers(Long accountId, LocalDateTime activeDate);

    LimitOffer findByLimitOfferId(int limitOfferId);
}



