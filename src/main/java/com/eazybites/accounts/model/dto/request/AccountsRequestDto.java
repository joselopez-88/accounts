package com.eazybites.accounts.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
@Data
public class AccountsRequestDto {
    @NotNull
    @Pattern(regexp = "(^[0-9]{10}$)", message = " The account number must be 10 digits.")
    private Long accountNumber;
    @NotBlank(message = " The account type is required.")
    private String accountType;
    @NotBlank(message = " The branch address is required.")
    private String branchAddress;

}
