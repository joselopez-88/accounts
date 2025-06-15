package com.eazybites.accounts.model.dto.request;

import lombok.Data;

@Data   
public class CustomerAccountUpdateRequestDto {
    private Long customerId;
    private String name;
    private String email;
    private String mobileNumber;
    private AccountsRequestDto account;
}

