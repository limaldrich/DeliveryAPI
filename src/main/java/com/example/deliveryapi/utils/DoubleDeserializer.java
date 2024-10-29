package com.example.deliveryapi.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class DoubleDeserializer extends JsonDeserializer<Double> {

    @Override
    public Double deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String value = jp.getText();

        try {
            double d = Double.parseDouble(value);
            if (d <= 0) {
                throw new RuntimeException(value + " must be more than 0.");
            }
            return d;
        } catch (NumberFormatException e) {
            throw new RuntimeException(value + " is not a number.");
        }
    }
}
