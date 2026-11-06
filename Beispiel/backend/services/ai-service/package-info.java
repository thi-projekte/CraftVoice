/*
 * =====================================================================================
 * [Squad P] KOPIERT AUS:
 *   backend/services/ai-service/src/main/java/de/winfprojekt/craftvoice/aiservice/package-info.java
 * NEUER ORT: Wurzel des ai-service (Root-Package)
 * -------------------------------------------------------------------------------------
 * WAS JETZT IN DIESER DATEI ZU TUN IST:
 *   Die Substruktur im Javadoc unten beschreibt noch die ALTEN Packages. Neue Zuordnung:
 *     api      -> api             (unverändert)
 *     model    -> domain
 *     pipeline -> application
 *     client   -> infrastructure
 *   -> {@link}-Verweise und Beschreibung entsprechend anpassen.
 *
 * Nicht mitkopiert (laut Absprache): pom.xml, Dockerfile, docker-compose.yml, mvnw, README,
 * eval/, docs/ und alle Tests (src/test). Die liegen weiterhin in backend/services/ai-service.
 * =====================================================================================
 */
/**
 * Root-Package des ai-service.
 *
 * <p>Der ai-service ist ein stateless HTTP-Service, der von der Camunda Process Engine
 * per HTTP-Connector aufgerufen wird (POST /ai/process). Er verarbeitet Sprachschnipsel
 * bzw. Korrekturen über zwei LLM-Calls und sendet das strukturierte Ergebnis als
 * {@code ergebnisKI}-Message per {@code businessKey} an die Engine zurück.
 *
 * <p>Substruktur:
 * <ul>
 *   <li>{@link de.winfprojekt.craftvoice.aiservice.api}      – REST-Endpoints (eingehend)</li>
 *   <li>{@link de.winfprojekt.craftvoice.aiservice.model}    – DTOs für Request/Response/Pipeline</li>
 *   <li>{@link de.winfprojekt.craftvoice.aiservice.pipeline} – LLM-Pipeline-Logik (Call 1 + Call 2)</li>
 *   <li>{@link de.winfprojekt.craftvoice.aiservice.client}   – REST-Clients (Camunda, MegaLLM, Catalog)</li>
 * </ul>
 */
package de.winfprojekt.craftvoice.aiservice;
