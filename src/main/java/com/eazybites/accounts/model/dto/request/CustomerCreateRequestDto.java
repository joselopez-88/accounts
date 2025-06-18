package com.eazybites.accounts.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerCreateRequestDto {
    @NotBlank(message = " The name is required.")
    @Size(min = 5, max = 30, message = " The Customer name must be between 5 and 30 characters.")
    private String name;
    @NotBlank(message = " The email is required.")
    @Email(message = " The email format is invalid.")
    private String email;
    @NotBlank(message = " The mobile number is required.")
    @Pattern(regexp = "(^[0-9]{10}$)", message = " The mobile number must be 10 digits.")
    private String mobileNumber;
}
