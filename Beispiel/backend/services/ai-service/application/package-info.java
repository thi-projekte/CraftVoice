/*
 * =====================================================================================
 * [Squad P] KOPIERT AUS:
 *   backend/services/ai-service/src/main/java/de/winfprojekt/craftvoice/aiservice/pipeline/package-info.java
 * NEUE SCHICHT: application  (früher Package "pipeline")
 * -------------------------------------------------------------------------------------
 * WAS JETZT IN DIESER DATEI ZU TUN IST:
 *   Package-Zeile unten ändern:
 *     de.winfprojekt.craftvoice.aiservice.pipeline -> de.winfprojekt.craftvoice.aiservice.application
 *   (gilt für ALLE Dateien in diesem Ordner).
 *
 * HINWEIS ZUR SCHICHTENZUORDNUNG in diesem Ordner:
 *   - MegaLlmService.java und CatalogSearchService.java sind eher technische Adapter
 *     (HTTP/Retry bzw. Mock-Katalog + REST-Client) -> mittelfristig nach infrastructure/,
 *     siehe jeweiliger Dateikopf.
 *   - Der eigentliche Use-Case (Ablauf Call 1 -> Call 2 -> Ergebnis an Engine) liegt noch
 *     in api/ProcessResource.java und soll nach application/AnalyzeVoiceInputUseCase.java.
 * =====================================================================================
 */
/**
 * LLM-Pipeline-Logik des ai-service.
 *
 * <p>Orchestriert die zwei LLM-Calls für Erstangebot bzw. Korrektur:
 * <ul>
 *   <li><b>LLM-Call 1 (Extraktion):</b> Sprachschnipsel + Vorlage → Suchparameter
 *       pro Position (siehe Ticket #538)</li>
 *   <li><b>LLM-Call 2 (Produktauswahl):</b> Kandidatenliste + Schnipsel-Kontext →
 *       passende Produktreferenz pro Position (siehe Ticket #541)</li>
 * </ul>
 *
 * <p><b>Wichtiger Constraint (Datenschutz):</b> Preise werden der KI NIE übergeben.
 * Der Preis wird erst nach LLM-Call 2 nachgelagert aus dem catalog-service geholt.
 *
 * <p>In der Stub-Phase (siehe Ticket #532) liefert diese Schicht eine fest verdrahtete,
 * gültige Antwort, damit das BPMN-Team integrieren kann, bevor die echte KI dranhängt.
 */
package de.winfprojekt.craftvoice.aiservice.pipeline;
