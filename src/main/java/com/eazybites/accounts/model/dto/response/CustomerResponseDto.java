package com.eazybites.accounts.model.dto.response;

import lombok.Data;

@Data
public class CustomerResponseDto {
    private Long customerId;
    private String name;
    private String email;
    private String mobileNumber;

    private AccountResponseDto account;
}
