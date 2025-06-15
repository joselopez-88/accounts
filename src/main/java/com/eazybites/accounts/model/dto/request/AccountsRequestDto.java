package com.eazybites.accounts.model.dto.request;

import lombok.Data;
@Data
public class AccountsRequestDto {
    private Long accountNumber;
    private String accountType;
    private String branchAddress;

}
