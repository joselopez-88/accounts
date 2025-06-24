package com.eazybites.accounts.exception.dto;

// import io.swagger.v3.oas.annotations.media.Schema;
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
// @Schema(name = "ErrorResponse", description = "Generic error response with a message")
public class ErrorResponse extends BaseErrorResponse {
    private String message;
}
