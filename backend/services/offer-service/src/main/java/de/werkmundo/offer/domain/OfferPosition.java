package de.werkmundo.offer.domain;

import java.math.BigDecimal;

public class OfferPosition {
    private Long id;
    private Offer offer;
    private OfferPositionType type;
    private String name;
    private String description;
    private String manufacturer;
    private BigDecimal quantity;
    private String unit;
    private String catalogProductId;
    private BigDecimal unitPrice;
    private BigDecimal lineTotal;
    private int positionOrder;

    public OfferPosition(
            OfferPositionType type,
            String name,
            String description,
            String manufacturer,
            BigDecimal quantity,
            String unit,
            String catalogProductId,
            BigDecimal unitPrice,
            BigDecimal lineTotal,
            int positionOrder) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
        this.quantity = quantity;
        this.unit = unit;
        this.catalogProductId = catalogProductId;
        this.unitPrice = unitPrice;
        this.lineTotal = lineTotal;
        this.positionOrder = positionOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public OfferPositionType getType() {
        return type;
    }

    public void setType(OfferPositionType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCatalogProductId() {
        return catalogProductId;
    }

    public void setCatalogProductId(String catalogProductId) {
        this.catalogProductId = catalogProductId;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal(BigDecimal lineTotal) {
        this.lineTotal = lineTotal;
    }

    public int getPositionOrder() {
        return positionOrder;
    }

    public void setPositionOrder(int positionOrder) {
        this.positionOrder = positionOrder;
    }
}
