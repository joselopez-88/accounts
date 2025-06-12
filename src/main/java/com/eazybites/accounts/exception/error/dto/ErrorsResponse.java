package com.eazybites.accounts.exception.error.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorsResponse extends BaseErrorResponse {
    private Map<String, String> errors;
}
