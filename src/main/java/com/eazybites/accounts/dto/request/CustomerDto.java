package com.eazybites.accounts.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CustomerDto {
private Long customerId;
    private String name;
    private String email;
    private String mobileNumber;
}
