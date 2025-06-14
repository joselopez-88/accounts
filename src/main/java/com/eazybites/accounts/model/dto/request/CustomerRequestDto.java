package com.eazybites.accounts.model.dto.request;

import lombok.Data;

@Data
public class CustomerRequestDto {
    private String name;
    private String email;
    private String mobileNumber;
}
