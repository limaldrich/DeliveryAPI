package com.example.deliveryapi.requests;

import com.example.deliveryapi.utils.DoubleDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

@Data
public class ItemRequest {
    @JsonDeserialize(using = DoubleDeserializer.class)
    private double weight;
    @JsonDeserialize(using = DoubleDeserializer.class)
    private double height;
    @JsonDeserialize(using = DoubleDeserializer.class)
    private double length;
    @JsonDeserialize(using = DoubleDeserializer.class)
    private double width;
    private String voucherCode;
}
