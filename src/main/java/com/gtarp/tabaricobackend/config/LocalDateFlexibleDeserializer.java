package com.gtarp.tabaricobackend.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateFlexibleDeserializer extends JsonDeserializer<LocalDate> {

    // Choisis le fuseau que tu veux considérer comme "référence"
    // Europe/Paris évite le J-1 si le front envoie du "Z" (UTC à minuit)
    private static final ZoneId TARGET_ZONE = ZoneId.systemDefault(); // ou ZoneId.of("Europe/Paris")
    private static final DateTimeFormatter DATE = DateTimeFormatter.ISO_LOCAL_DATE;

    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonToken t = p.currentToken();
        if (t == JsonToken.VALUE_NULL) return null;

        String s = p.getValueAsString();
        if (s == null || s.isBlank()) return null;

        s = s.trim();

        // Cas 1 : "yyyy-MM-dd" → direct
        if (!s.contains("T")) {
            return LocalDate.parse(s, DATE);
        }

        // Cas 2 : ISO datetime (avec Z/offset ou pas)
        // - Si "Z" ou offset → on parse en OffsetDateTime
        // - Sinon on tente LocalDateTime puis on applique TARGET_ZONE
        try {
            if (s.endsWith("Z") || s.contains("+") || s.lastIndexOf('-') > 7) {
                // a priori OffsetDateTime ("Z" ou "+02:00", etc.)
                OffsetDateTime odt = OffsetDateTime.parse(s);
                return odt.atZoneSameInstant(TARGET_ZONE).toLocalDate();
            } else {
                // Pas d'offset explicite → on suppose que c'est du local datetime
                LocalDateTime ldt = LocalDateTime.parse(s);
                return ldt.atZone(TARGET_ZONE).toLocalDate();
            }
        } catch (DateTimeParseException e) {
            // Dernier recours : on prend juste la partie date
            return LocalDate.parse(s.substring(0, 10), DATE);
        }
    }
}
