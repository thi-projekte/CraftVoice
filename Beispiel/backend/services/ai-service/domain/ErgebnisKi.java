/*
 * [Squad P] KOPIERT AUS: backend/services/ai-service/src/main/java/de/winfprojekt/craftvoice/aiservice/model/ErgebnisKi.java
 * NEUE SCHICHT: domain  (ersetzt den leeren Platzhalter domain/AiAnalysisResult.java)
 * TODO:
 *   1. Package "...aiservice.model" -> "...aiservice.domain" (bewusst noch nicht geändert).
 *   2. Diese Struktur als JSON-Schema in Beispiel/contracts/ai/AiAnalysisResult.json
 *      festhalten (Datei ist noch leer) und mit Squad V abstimmen - die Engine liest
 *      daraus ergebnisKI.strukturierteAngebotspositionen.
 */
package de.winfprojekt.craftvoice.aiservice.model;

import java.util.List;

/**
 * Ergebnis der KI-Verarbeitung. Wird vom ai-service erzeugt und per
 * {@code ergebnisKI}-Message (mit {@code businessKey}) an die Process Engine
 * korreliert.
 *
 * <p>Struktur laut Schnittstellenvertrag (Stand 29.05.2026). Die Process Engine
 * extrahiert aus diesem Objekt anschließend {@code ergebnisKI.strukturierteAngebotspositionen}:
 * <pre>
 * {
 *   "strukturierteAngebotspositionen": {
 *     "leistungen": [ Position, ... ],
 *     "material":   [ Position, ... ],
 *     "notizen":    [ "...", "..." ]
 *   },
 *   "korrekturvorschlaege": [ "Hinweis 1", "Hinweis 2" ],
 *   "geschaetzteArbeitsdauerStunden": 2.5
 * }
 * </pre>
 *
 * <p><b>Wichtige Änderung ggü. früherem Stand:</b> {@code strukturierteAngebotspositionen}
 * ist ein Objekt ({@link Angebotspositionen}) mit {@code leistungen}/{@code material}/
 * {@code notizen} — NICHT mehr eine flache Positions-Liste.
 *
 * <p><b>{@code geschaetzteArbeitsdauerStunden}</b> (optional): Gesamt-Arbeitsdauer in Stunden,
 * die der offer-service in eine Arbeitszeit-Position umrechnet ({@code Stunden × Stundensatz},
 * PR #652). <b>Die KI schätzt NICHT von sich aus</b> — das Feld wird nur gefüllt, wenn der
 * Handwerker eine Dauer ausspricht; sonst {@code null} (der Handwerker trägt die Stunden dann
 * in der UI ein). Stunden sind <i>Aufwand</i>, kein Preis — der Datenschutz-Constraint bleibt
 * gewahrt.
 *
 * <p><b>Datenschutz-Constraint:</b> Die enthaltenen {@link Position}en tragen bewusst
 * KEIN {@code preis}-Feld — Preise werden der KI nicht übergeben und nicht von ihr bestimmt.
 *
 * @param strukturierteAngebotspositionen erzeugte (oder korrigierte) Positionen
 * @param korrekturvorschlaege            optionale Hinweise/Rückfragen an den Handwerker
 * @param geschaetzteArbeitsdauerStunden  ausgesprochene Gesamt-Arbeitsdauer in Stunden, sonst {@code null}
 */
public record ErgebnisKi(
        Angebotspositionen strukturierteAngebotspositionen,
        List<String> korrekturvorschlaege,
        Double geschaetzteArbeitsdauerStunden
) {

    /**
     * Convenience-Konstruktor ohne Arbeitsdauer — fuer den Stub und Faelle, in denen keine
     * Dauer ausgesprochen wurde. {@code geschaetzteArbeitsdauerStunden} ist dann {@code null}.
     */
    public ErgebnisKi(Angebotspositionen strukturierteAngebotspositionen,
                      List<String> korrekturvorschlaege) {
        this(strukturierteAngebotspositionen, korrekturvorschlaege, null);
    }
}
