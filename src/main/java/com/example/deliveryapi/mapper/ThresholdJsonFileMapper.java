package com.example.deliveryapi.mapper;

import com.example.deliveryapi.model.Thresholds;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Service
public class ThresholdJsonFileMapper {

    private Thresholds thresholds;
    @Value("thresholds.json")
    private String jsonPath;

    public Thresholds getThresholds() {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream targetStream = new ClassPathResource(jsonPath).getInputStream()) {
            return objectMapper.readValue(targetStream, Thresholds.class);
        } catch (IOException e) {
            log.error("Error reading json file: " + jsonPath, e);
            throw new RuntimeException("Error reading json file: " + jsonPath, e);
        }
    }
}
