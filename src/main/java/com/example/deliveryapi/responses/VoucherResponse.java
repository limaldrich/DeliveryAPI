package com.example.deliveryapi.responses;

import lombok.Data;

@Data
public class VoucherResponse {
    private String code;
    private Double discount;
    private String expiryDate;
}
