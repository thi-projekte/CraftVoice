package de.werkmundo.offer.application;

import de.werkmundo.offer.domain.Offer;
import de.werkmundo.offer.domain.OfferRepository;
import java.util.Objects;
import java.util.NoSuchElementException;

public class UpdateOfferUseCase {
    private final OfferRepository offerRepository;

    public UpdateOfferUseCase(OfferRepository offerRepository) {
        this.offerRepository = Objects.requireNonNull(offerRepository, "offerRepository must not be null");
    }

    public Offer execute(Offer offer) {
        Offer updatedOffer = Objects.requireNonNull(offer, "offer must not be null");
        Offer existingOffer = offerRepository.findByBusinessKey(updatedOffer.getBusinessKey())
                .orElseThrow(() -> new NoSuchElementException(
                        "Offer not found: " + updatedOffer.getBusinessKey()));

        existingOffer.updateDetailsFrom(updatedOffer);
        return offerRepository.save(existingOffer);
    }
}