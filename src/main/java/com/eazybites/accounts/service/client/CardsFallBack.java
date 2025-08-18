package com.eazybites.accounts.service.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.eazybites.accounts.model.dto.client.CardsResponseDto;
@Component
public class CardsFallBack implements CardsFeignClient{

    @Override
    public ResponseEntity<CardsResponseDto> fetchCardDetails(String correlationId, String mobileNumber) {
    //    return ResponseEntity.ok(new CardsResponseDto());
        return null;
    }

}
