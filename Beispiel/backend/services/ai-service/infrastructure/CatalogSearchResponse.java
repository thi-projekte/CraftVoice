/*
 * [Squad P] KOPIERT AUS: backend/services/ai-service/src/main/java/de/winfprojekt/craftvoice/aiservice/client/CatalogSearchResponse.java
 * NEUE SCHICHT: infrastructure  (Antwort-DTO des catalog-service)
 * TODO:
 *   1. Package "...aiservice.client" -> "...aiservice.infrastructure" (bewusst noch nicht geändert).
 */
package de.winfprojekt.craftvoice.aiservice.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Antwort des catalog-service-Such-Endpoints ({@code GET /catalog/material/search}, PR #701).
 *
 * <p>Form laut Spec ({@code docs/catalog-search-spec.md}): eine nach Relevanz absteigend
 * sortierte Kandidatenliste; leere Liste, wenn nichts passt (→ die KI kann ablehnen).
 *
 * @param candidates Kandidaten (Top-k, ohne Preise — siehe {@link CatalogCandidate})
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record CatalogSearchResponse(
        List<CatalogCandidate> candidates
) {}
