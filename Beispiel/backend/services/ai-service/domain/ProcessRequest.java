/*
 * [Squad P] KOPIERT AUS: backend/services/ai-service/src/main/java/de/winfprojekt/craftvoice/aiservice/model/ProcessRequest.java
 * NEUE SCHICHT: domain (vorläufig)  (ersetzt den leeren Platzhalter domain/AiAnalysis.java)
 * TODO:
 *   1. Package "...aiservice.model" -> "...aiservice.domain" (bewusst noch nicht geändert).
 *   2. Das ist ein reines HTTP-Request-DTO von POST /ai/process -> sauberer in api/dto/.
 *   3. Als JSON-Schema in Beispiel/contracts/ai/AiAnalysisRequest.json festhalten (Datei ist
 *      noch leer) und mit Squad V abstimmen (die Engine baut diesen Payload).
 */
package de.winfprojekt.craftvoice.aiservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Eingangs-Payload für {@code POST /ai/process}, wie ihn die Process Engine per
 * HTTP-Connector schickt.
 *
 * <p>Die exakte Form ist im Schnittstellenvertrag „Process Engine → KI-Schnittstelle“
 * (Stand 29.05.2026) festgelegt. Wichtig zu wissen:
 * <ul>
 *   <li><b>Keine {@code customerId} / {@code kundendaten}</b> — die Kundenreferenz
 *       wird laut Doku NICHT an die KI übergeben.</li>
 *   <li><b>Kein {@code processInstanceId}</b> — nur {@code businessKey} wird
 *       für die Korrelation mitgegeben.</li>
 * </ul>
 *
 * <p><b>Fallunterscheidung über Feld-Anwesenheit</b> (kein {@code typ}-Feld):
 * <ul>
 *   <li><b>Erstangebot:</b> {@code vorlage} + {@code sprachschnipsel} sind gesetzt.</li>
 *   <li><b>Angebotskorrektur:</b> {@code strukturierteAngebotspositionen} +
 *       {@code korrekturschnipsel} sind gesetzt.</li>
 * </ul>
 *
 * <p><b>Hinweis zur Korrektur:</b> Frühere Doku-Stände nannten dieses Feld
 * {@code angebotsentwurf}. Laut aktuellem Script der Process Engine heißt es
 * {@code strukturierteAngebotspositionen} und trägt dieselbe Objektstruktur wie eine
 * {@link Vorlage} ({@code leistungen}/{@code material}/{@code notizen}) — siehe
 * {@link Angebotspositionen}.
 *
 * <p><b>Nicht implementiert — Rechnungskorrektur:</b> Der Vertrag kennt eine dritte
 * Variante ({@code rechnungsentwurf} + {@code korrekturschnipselRechnung}). Sie ist hier
 * bewusst NICHT abgebildet: (1) Die Doku markiert ihre fachliche Zuordnung zum
 * Angebotsprozess selbst als „noch zu klären“, und (2) ihr Payload enthält Preise
 * ({@code preis}), was unserem harten Datenschutz-Constraint widerspricht. Kommt sie
 * über diesen Endpoint herein, lehnt der {@code ProcessTypeDetector} sie mit einem
 * sprechenden Fehler ab, statt sie still falsch zu verarbeiten.
 *
 * <p>Die konkrete Routing-Logik liegt in
 * {@link de.winfprojekt.craftvoice.aiservice.pipeline.ProcessTypeDetector}.
 *
 * <p>{@code @JsonIgnoreProperties(ignoreUnknown = true)} sorgt dafür, dass zusätzliche
 * Felder der Process Engine den Parser nicht abbrechen.
 *
 * @param businessKey                     Korrelations-ID der Prozessinstanz. MUSS in der
 *                                        ergebnisKI-Message wieder mitgegeben werden.
 * @param prompt                          Vom BPMN mitgegebener Aufgaben-Hinweis (z.B.
 *                                        "Erstelle ein Erstangebot anhand von Vorlage und
 *                                        Sprachschnipsel."). Wird später im LLM-Prompt
 *                                        verwendet.
 * @param vorlage                         Strukturierte Vorlage (Erstangebot-Fall).
 * @param sprachschnipsel                 Roher Sprach-Input vom Handwerker (Erstangebot-Fall).
 * @param strukturierteAngebotspositionen Bestehende Positionen, die überarbeitet werden
 *                                        sollen (Korrektur-Fall).
 * @param korrekturschnipsel              Sprach-Input mit Korrekturwünschen (Korrektur-Fall).
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ProcessRequest(
        String businessKey,
        String prompt,
        Vorlage vorlage,
        String sprachschnipsel,
        Angebotspositionen strukturierteAngebotspositionen,
        String korrekturschnipsel
) {}
