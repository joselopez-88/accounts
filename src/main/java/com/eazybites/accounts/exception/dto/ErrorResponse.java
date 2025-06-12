package com.eazybites.accounts.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse extends BaseErrorResponse {
    private String message;
}
