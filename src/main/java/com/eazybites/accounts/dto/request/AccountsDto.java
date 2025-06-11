package com.eazybites.accounts.dto.request;

import lombok.Data;

@Data
public class AccountsDto {
private Long accountNumber;
    private String accountType;
    private String branchAddress;
    private Long customerId;

}
