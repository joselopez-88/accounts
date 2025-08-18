package com.eazybites.accounts.service.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.eazybites.accounts.model.dto.client.LoansResponseDto;
@Component
public class LoansFallBack implements LoansFeignClient {

    @Override
    public ResponseEntity<LoansResponseDto> fetchLoan(String correlationId, String mobileNumber) {
        // return ResponseEntity.ok(new LoansResponseDto());
        return null;
    }

}
