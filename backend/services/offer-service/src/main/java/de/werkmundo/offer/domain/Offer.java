package de.werkmundo.offer.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Offer {
    private final String businessKey;
    private String customerId;
    private String craftsmanId;
    private String acceptanceToken;
    private BigDecimal totalPrice;
    private BigDecimal estimatedWorkDurationHours;
    private final List<String> correctionSuggestions = new ArrayList<>();
    private OfferStatus status;
    private final List<OfferPosition> positions = new ArrayList<>();
    private final List<OfferStatusHistory> statusHistory = new ArrayList<>();
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Offer(String businessKey) {
        this.businessKey = Objects.requireNonNull(businessKey, "businessKey must not be null");
        this.status = OfferStatus.DRAFT;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = createdAt;
        this.statusHistory.add(new OfferStatusHistory(status, createdAt));
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
        updateTimestamp();
    }

    public String getCraftsmanId() {
        return craftsmanId;
    }

    public void setCraftsmanId(String craftsmanId) {
        this.craftsmanId = craftsmanId;
        updateTimestamp();
    }

    public String getAcceptanceToken() {
        return acceptanceToken;
    }

    public void setAcceptanceToken(String acceptanceToken) {
        this.acceptanceToken = acceptanceToken;
        updateTimestamp();
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        updateTimestamp();
    }

    public BigDecimal getEstimatedWorkDurationHours() {
        return estimatedWorkDurationHours;
    }

    public void setEstimatedWorkDurationHours(BigDecimal estimatedWorkDurationHours) {
        this.estimatedWorkDurationHours = estimatedWorkDurationHours;
        updateTimestamp();
    }

    public List<String> getCorrectionSuggestions() {
        return Collections.unmodifiableList(correctionSuggestions);
    }

    public void addCorrectionSuggestion(String correctionSuggestion) {
        correctionSuggestions.add(Objects.requireNonNull(correctionSuggestion, "correctionSuggestion must not be null"));
        updateTimestamp();
    }

    public OfferStatus getStatus() {
        return status;
    }

    public void setStatus(OfferStatus status) {
        OfferStatus newStatus = Objects.requireNonNull(status, "status must not be null");
        if (this.status != newStatus) {
            this.status = newStatus;
            LocalDateTime changedAt = LocalDateTime.now();
            this.statusHistory.add(new OfferStatusHistory(newStatus, changedAt));
            this.updatedAt = changedAt;
        }
    }

    public List<OfferPosition> getPositions() {
        return Collections.unmodifiableList(positions);
    }

    public void addPosition(OfferPosition position) {
        OfferPosition newPosition = Objects.requireNonNull(position, "position must not be null");
        newPosition.setOffer(this);
        positions.add(newPosition);
        updateTimestamp();
    }

    public void replacePositions(List<OfferPosition> newPositions) {
        List<OfferPosition> positionsToSet = List.copyOf(
                Objects.requireNonNull(newPositions, "positions must not be null"));
        positions.forEach(position -> position.setOffer(null));
        positions.clear();
        positionsToSet.forEach(this::addPosition);
        updateTimestamp();
    }

    public void replaceCorrectionSuggestions(List<String> newCorrectionSuggestions) {
        List<String> suggestionsToSet = List.copyOf(
                Objects.requireNonNull(newCorrectionSuggestions, "correctionSuggestions must not be null"));
        correctionSuggestions.clear();
        correctionSuggestions.addAll(suggestionsToSet);
        updateTimestamp();
    }

    public void updateDetailsFrom(Offer updatedOffer) {
        Objects.requireNonNull(updatedOffer, "updatedOffer must not be null");
        if (!businessKey.equals(updatedOffer.businessKey)) {
            throw new IllegalArgumentException("businessKey cannot be changed");
        }
        if (updatedOffer == this) {
            return;
        }

        customerId = updatedOffer.customerId;
        craftsmanId = updatedOffer.craftsmanId;
        acceptanceToken = updatedOffer.acceptanceToken;
        totalPrice = updatedOffer.totalPrice;
        estimatedWorkDurationHours = updatedOffer.estimatedWorkDurationHours;
        correctionSuggestions.clear();
        correctionSuggestions.addAll(updatedOffer.correctionSuggestions);
        replacePositions(updatedOffer.positions);
        updateTimestamp();
    }

    public List<OfferStatusHistory> getStatusHistory() {
        return Collections.unmodifiableList(statusHistory);
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    private void updateTimestamp() {
        this.updatedAt = LocalDateTime.now();
    }
}