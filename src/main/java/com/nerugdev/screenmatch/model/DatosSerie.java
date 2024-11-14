package com.nerugdev.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosSerie(
        @JsonAlias("Title") String titulo,
        @JsonAlias("totalSeasons") @JsonDeserialize(using = DatosSerie.CustomIntegerDeserializer.class) Integer totalDeTemporadas,
        @JsonAlias("imdbRating") String evaluacion) {

    public static class CustomIntegerDeserializer extends JsonDeserializer<Integer> {
        @Override
        public Integer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            String text = p.getText();
            if ("N/A".equals(text) ||  "null".equals(text)) {
                return 0; // Valor predeterminado para "N/A"
            }
            return Integer.valueOf(text);
        }
    }
}
