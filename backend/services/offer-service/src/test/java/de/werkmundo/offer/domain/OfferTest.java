package de.werkmundo.offer.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;

class OfferTest {

    @Test
    void newOfferStartsAsDraftAndRecordsInitialStatus() {
        Offer offer = new Offer("business-123");

        assertEquals("business-123", offer.getBusinessKey());
        assertEquals(OfferStatus.DRAFT, offer.getStatus());
        assertEquals(1, offer.getStatusHistory().size());
        assertEquals(OfferStatus.DRAFT, offer.getStatusHistory().get(0).getStatus());
        assertNotNull(offer.getCreatedAt());
        assertNotNull(offer.getUpdatedAt());
    }

    @Test
    void changingStatusAddsStatusHistoryEntry() {
        Offer offer = new Offer("business-123");

        offer.setStatus(OfferStatus.APPROVED);

        assertEquals(OfferStatus.APPROVED, offer.getStatus());
        assertEquals(2, offer.getStatusHistory().size());
        assertEquals(OfferStatus.APPROVED, offer.getStatusHistory().get(1).getStatus());
        assertNotNull(offer.getStatusHistory().get(1).getChangedAt());
    }

    @Test
    void settingSameStatusDoesNotAddDuplicateHistoryEntry() {
        Offer offer = new Offer("business-123");

        offer.setStatus(OfferStatus.DRAFT);

        assertEquals(1, offer.getStatusHistory().size());
    }

    @Test
    void offerStoresPositionsAndCorrectionSuggestions() {
        Offer offer = new Offer("business-123");
        OfferPosition position = new OfferPosition(
                OfferPositionType.LABOR,
                "Installation",
                "Install the equipment",
                "Werkmundo",
                BigDecimal.ONE,
                "hour",
                null,
                new BigDecimal("75.00"),
                new BigDecimal("75.00"),
                1);

        offer.addPosition(position);
        offer.addCorrectionSuggestion("Confirm installation date");

        assertEquals(List.of(position), offer.getPositions());
        assertEquals(List.of("Confirm installation date"), offer.getCorrectionSuggestions());
    }

    @Test
    void returnedListsCannotBeModified() {
        Offer offer = new Offer("business-123");

        assertThrows(UnsupportedOperationException.class,
                () -> offer.getPositions().add(new OfferPosition(
                        OfferPositionType.OTHER, "Item", null, null, BigDecimal.ONE, "item", null,
                        BigDecimal.ZERO, BigDecimal.ZERO, 1)));
        assertThrows(UnsupportedOperationException.class,
                () -> offer.getCorrectionSuggestions().add("Suggestion"));
        assertThrows(UnsupportedOperationException.class,
                () -> offer.getStatusHistory().clear());
    }
}
