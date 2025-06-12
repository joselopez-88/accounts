package com.eazybites.accounts.dto.request;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class AccountsDto {
private Long accountNumber;
    private String accountType;
    private String branchAddress;
    private Long customerId;

}
