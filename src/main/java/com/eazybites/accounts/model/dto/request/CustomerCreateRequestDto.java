package com.eazybites.accounts.model.dto.request;

import lombok.Data;

@Data
public class CustomerCreateRequestDto {
    private String name;
    private String email;
    private String mobileNumber;
}
