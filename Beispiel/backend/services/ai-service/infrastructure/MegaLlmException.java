/*
 * [Squad P] KOPIERT AUS: backend/services/ai-service/src/main/java/de/winfprojekt/craftvoice/aiservice/client/MegaLlmException.java
 * NEUE SCHICHT: infrastructure
 * TODO:
 *   1. Package "...aiservice.client" -> "...aiservice.infrastructure" (bewusst noch nicht geändert).
 *   2. Wird auch in application/ geworfen/gefangen (ErgebnisKiParser, Call1PromptBuilder,
 *      LlmCall1Generator). Optional anbieterneutral umbenennen (z.B. LlmException) und nach
 *      application/ verschieben, damit application nicht von infrastructure abhängt.
 */
package de.winfprojekt.craftvoice.aiservice.client;

/**
 * Signalisiert einen fehlgeschlagenen LLM-Aufruf (HTTP-Fehler nach Retries, Timeout,
 * leere oder unparsbare Antwort).
 *
 * <p>Wird vom {@link de.winfprojekt.craftvoice.aiservice.pipeline.MegaLlmService} und vom
 * {@link de.winfprojekt.craftvoice.aiservice.pipeline.ErgebnisKiParser} geworfen und vom
 * {@link de.winfprojekt.craftvoice.aiservice.pipeline.LlmCall1Generator} gefangen, der
 * dann auf den Stub-Fallback ausweicht.
 */
public class MegaLlmException extends RuntimeException {

    public MegaLlmException(String message) {
        super(message);
    }

    public MegaLlmException(String message, Throwable cause) {
        super(message, cause);
    }
}
