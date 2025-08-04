package com.eazybites.accounts.model.dto.response;

import com.eazybites.accounts.model.dto.client.CardsResponseDto;
import com.eazybites.accounts.model.dto.client.LoansResponseDto;

import lombok.Data;

@Data
public class CustomerDetailsDto {

    private CustomerResponseDto customer;
    private CardsResponseDto cards;
    private LoansResponseDto loans;
}
