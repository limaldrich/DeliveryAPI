package com.example.deliveryapi.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemResponse {
    private Double deliveryCost;
}
