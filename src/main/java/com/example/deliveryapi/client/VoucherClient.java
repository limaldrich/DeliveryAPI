package com.example.deliveryapi.client;

import com.example.deliveryapi.responses.VoucherResponse;
import jakarta.validation.constraints.NotBlank;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name ="VoucherClient", url = "${voucher.api.url}")
public interface VoucherClient {
    @GetMapping(value = "/{voucherCode}?key=apikey", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<VoucherResponse> getVoucher(@PathVariable("voucherCode") @NotBlank String voucherCode);
}
