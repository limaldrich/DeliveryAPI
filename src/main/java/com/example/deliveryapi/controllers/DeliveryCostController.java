package com.example.deliveryapi.controllers;

import com.example.deliveryapi.requests.ItemRequest;
import com.example.deliveryapi.responses.ItemResponse;
import com.example.deliveryapi.responses.VoucherResponse;
import com.example.deliveryapi.services.DeliveryService;
import com.example.deliveryapi.services.VoucherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/delivery")
@Slf4j
public class DeliveryCostController {

    @Autowired
    private DeliveryService deliveryService;
    @Autowired
    private VoucherService voucherService;

    @PostMapping("/cost")
    public ItemResponse calculateDeliveryCost(@RequestBody ItemRequest itemRequest) {
        Double cost = deliveryService.calculateDeliveryCost(itemRequest);
        if (itemRequest.getVoucherCode() != null && !itemRequest.getVoucherCode().trim().isEmpty()) {
            Double discount = applyVoucher(itemRequest.getVoucherCode());
            cost = cost - discount;
        }
        return ItemResponse.builder().deliveryCost(cost).build();
    }

    private Double applyVoucher(String voucherCode) {
        ResponseEntity<VoucherResponse> voucher = voucherService.validateVoucher(voucherCode);
        return Objects.requireNonNull(voucher.getBody()).getDiscount();
    }
}
