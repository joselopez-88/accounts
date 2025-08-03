package com.eazybites.accounts.model.dto.client;

import lombok.Data;

@Data
public class LoansResponseDto {
    private String mobileNumber;
    private String loanNumber;
    private String loanType;
    private int totalLoan;
    private int amountPaid;
    private int outstandingAmount;
}
