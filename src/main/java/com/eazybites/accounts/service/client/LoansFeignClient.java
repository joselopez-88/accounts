package com.eazybites.accounts.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.eazybites.accounts.model.dto.client.LoansResponseDto;

@FeignClient("loans")
public interface LoansFeignClient {

    @GetMapping(value = "/api/fetch", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponseDto> fetchLoan(@RequestHeader(name = "eazyBank-correlation-id") String correlationId, @RequestParam String mobileNumber);
}
