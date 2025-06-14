package com.eazybites.accounts.model.dto.response;

import lombok.Data;

@Data
public class AccountResponseDto {
    private Long accountNumber;
    private String accountType;
    private String branchAddress;
}
