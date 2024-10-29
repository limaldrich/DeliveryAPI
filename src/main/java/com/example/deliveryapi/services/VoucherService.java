package com.example.deliveryapi.services;


import com.example.deliveryapi.client.VoucherClient;
import com.example.deliveryapi.exception.BadRequestException;
import com.example.deliveryapi.responses.VoucherResponse;
import feign.RetryableException;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;


@Service
@Setter
@Slf4j
public class VoucherService {

    @Autowired
    private VoucherClient voucherClient;

    public ResponseEntity<VoucherResponse> validateVoucher(String voucherCode) {
        try {
            return voucherClient.getVoucher(voucherCode);
        } catch (Exception e) {
            RetryableException exception = (RetryableException) e;
            if (exception.status() == 400) {
                //voucher code error or I can add custom message
                throw new BadRequestException((e.getMessage()));
            } else {
                throw new RuntimeException("other error");
            }
        }
    }
}



