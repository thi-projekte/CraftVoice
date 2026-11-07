package de.werkmundo.offer.domain;

import java.util.Optional;

public interface OfferRepository {
    Offer save(Offer offer);

    Optional<Offer> findByBusinessKey(String businessKey);
}