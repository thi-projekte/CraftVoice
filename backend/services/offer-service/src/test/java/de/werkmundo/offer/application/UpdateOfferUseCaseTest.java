package de.werkmundo.offer.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.werkmundo.offer.domain.Offer;
import de.werkmundo.offer.domain.OfferPosition;
import de.werkmundo.offer.domain.OfferPositionType;
import de.werkmundo.offer.domain.OfferRepository;
import de.werkmundo.offer.domain.OfferStatus;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

class UpdateOfferUseCaseTest {

    @Test
    void updatesExistingOfferAndSavesIt() {
        Offer existingOffer = new Offer("business-123");
        existingOffer.setStatus(OfferStatus.APPROVED);
        Offer updatedData = new Offer("business-123");
        updatedData.setCustomerId("customer-456");
        updatedData.setCraftsmanId("craftsman-789");
        updatedData.setTotalPrice(new BigDecimal("25.00"));
        updatedData.addPosition(new OfferPosition(
                OfferPositionType.MATERIAL,
                "Wood",
                "Oak board",
                "Manufacturer",
                new BigDecimal("2"),
                "meter",
                "catalog-123",
                new BigDecimal("12.50"),
                new BigDecimal("25.00"),
                1));

        OfferRepository repository = new OfferRepository() {
            @Override
            public Offer save(Offer offer) {
                return offer;
            }

            @Override
            public Optional<Offer> findByBusinessKey(String businessKey) {
                return Optional.of(existingOffer);
            }
        };
        UpdateOfferUseCase useCase = new UpdateOfferUseCase(repository);

        Offer result = useCase.execute(updatedData);

        assertSame(existingOffer, result);
        assertEquals("customer-456", result.getCustomerId());
        assertEquals("craftsman-789", result.getCraftsmanId());
        assertEquals(new BigDecimal("25.00"), result.getTotalPrice());
        assertEquals(OfferStatus.APPROVED, result.getStatus());
        assertEquals(2, result.getStatusHistory().size());
        assertEquals(1, result.getPositions().size());
        assertSame(result, result.getPositions().get(0).getOffer());
        assertEquals("Wood", result.getPositions().get(0).getName());
    }

    @Test
    void throwsWhenOfferDoesNotExist() {
        OfferRepository repository = new OfferRepository() {
            @Override
            public Offer save(Offer offer) {
                return offer;
            }

            @Override
            public Optional<Offer> findByBusinessKey(String businessKey) {
                return Optional.empty();
            }
        };
        UpdateOfferUseCase useCase = new UpdateOfferUseCase(repository);

        assertThrows(NoSuchElementException.class,
                () -> useCase.execute(new Offer("missing-offer")));
    }
}
