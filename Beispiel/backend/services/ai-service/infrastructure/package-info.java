/*
 * =====================================================================================
 * [Squad P] KOPIERT AUS:
 *   backend/services/ai-service/src/main/java/de/winfprojekt/craftvoice/aiservice/client/package-info.java
 * NEUE SCHICHT: infrastructure  (früher Package "client")
 * -------------------------------------------------------------------------------------
 * WAS JETZT IN DIESER DATEI ZU TUN IST:
 *   Package-Zeile unten ändern:
 *     de.winfprojekt.craftvoice.aiservice.client -> de.winfprojekt.craftvoice.aiservice.infrastructure
 *   (gilt für ALLE Dateien in diesem Ordner).
 *
 * ZUORDNUNG DER PLATZHALTER:
 *   - CatalogClient.java  -> ersetzt durch CatalogSearchClient.java (gelöscht)
 *   - LlmClient.java      -> ersetzt durch MegaLlmClient.java (gelöscht)
 *   - SpeechToTextClient.java ist weiterhin LEER: Die Transkription läuft aktuell NICHT im
 *     ai-service, sondern im offer-service (Endpoint /speech-capture/transcribe, aufgerufen
 *     aus Beispiel/frontend/ViewModel/voice/useVoiceInput.ts).
 *     -> OFFEN: Mit Squad J klären, ob Speech-to-Text in den ai-service wandert. Falls ja,
 *        hier implementieren, sonst Platzhalter löschen.
 * =====================================================================================
 */
/**
 * REST-Clients für externe Systeme.
 *
 * <p>Alle ausgehenden HTTP-Aufrufe an andere Services werden hier gekapselt:
 * <ul>
 *   <li><b>Camunda Engine Client:</b> sendet {@code ergebnisKI}-Message per
 *       {@code businessKey} an die laufende Prozessinstanz (siehe Ticket #533).</li>
 *   <li><b>MegaLLM Client:</b> ruft das LLM für die beiden Pipeline-Stufen auf
 *       (siehe Tickets #538, #541).</li>
 *   <li><b>Catalog Client:</b> fragt Produktkandidaten beim catalog-service ab
 *       (Mock-Variante siehe #539, echter Client #540).</li>
 * </ul>
 *
 * <p>Konfiguration der URLs erfolgt über {@code application.properties}
 * ({@code camunda.engine.url}, {@code megallm.api.url}, {@code catalog.service.url}).
 */
package de.winfprojekt.craftvoice.aiservice.client;
