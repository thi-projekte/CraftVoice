/*
 * =====================================================================================
 * [Squad P] KOPIERT AUS:
 *   backend/services/ai-service/src/main/java/de/winfprojekt/craftvoice/aiservice/model/package-info.java
 * NEUE SCHICHT: domain  (früher Package "model")
 * -------------------------------------------------------------------------------------
 * WAS JETZT IN DIESER DATEI ZU TUN IST:
 *   Package-Zeile unten ändern:
 *     de.winfprojekt.craftvoice.aiservice.model -> de.winfprojekt.craftvoice.aiservice.domain
 *   (gilt für ALLE Dateien in diesem Ordner).
 *
 * HINWEIS: ProcessRequest.java und ProcessResponse.java sind reine HTTP-DTOs des Endpoints
 *   POST /ai/process -> sauberer wäre api/dto/. Fachliche Strukturen (ErgebnisKi,
 *   Angebotspositionen, Position, Vorlage, ProcessType) bleiben hier in domain/.
 *   Die Platzhalter AiAnalysis.java / AiAnalysisResult.java wurden durch
 *   ProcessRequest.java / ErgebnisKi.java ersetzt und gelöscht.
 * =====================================================================================
 */
/**
 * Daten-Transfer-Objekte (DTOs) für den ai-service.
 *
 * <p>Enthält die typisierten Repräsentationen aller JSON-Strukturen, die der Service
 * verarbeitet oder produziert:
 * <ul>
 *   <li>Eingangs-Payload der Process Engine (Kundendaten, Vorlage, Sprachschnipsel,
 *       Angebotsentwurf, Korrekturschnipsel, businessKey)</li>
 *   <li>Pipeline-interne Zwischenrepräsentationen (Suchparameter, Produktkandidaten)</li>
 *   <li>Ausgangs-Struktur {@code ergebnisKI} mit {@code strukturierteAngebotspositionen}
 *       (ohne Preis!) und {@code korrekturvorschlaege}</li>
 * </ul>
 *
 * <p><b>Wichtig:</b> DTOs sind plain Java-Records oder POJOs, keine Business-Logik.
 */
package de.winfprojekt.craftvoice.aiservice.model;
