package com.eazybites.accounts.exception.dto;

import java.util.Map;

// import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
// @Schema(name = "ErrorsResponse", description = "Show a map of errors")
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorsResponse extends BaseErrorResponse {
    // @Schema(name = "errors", description = "Map of errors with field name as key and error message as value")
    private Map<String, String> errors;
}
