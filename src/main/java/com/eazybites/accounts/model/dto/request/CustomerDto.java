package com.eazybites.accounts.model.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CustomerDto {
    private String name;
    private String email;
    private String mobileNumber;
}
