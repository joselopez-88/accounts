package com.eazybites.accounts.exception.dto;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
@Schema(
    description = "Standardized error response model for API errors",
    name = "ApiError"
)
@Getter
@Builder
public class ApiError {
    @Schema(description = "API path that generated the error", example = "/api/accounts")
    private String apiPath;
    @Schema(description = "HTTP status", example = "BAD_REQUEST")
    private String status;
    @Schema(description = "HTTP status code", example = "400")
    private Integer code;
    @Schema(description = "Timestamp when the error occurred", example = "2023-11-15T10:30:45")
    private LocalDateTime errorTime;

    @JsonUnwrapped
    private ErrorBody body;

    // public static ApiError createWithMessage(String apiPath, String status, Integer code, String message) {
    //    return ApiError.builder()
    //            .apiPath(apiPath)
    //            .status(status)
    //            .code(code)
    //            .errorTime(LocalDateTime.now())
    //            .body(new MessageBody(message)) 
    //            .build();    
    // }

        public static ApiError createWithMessage(String apiPath, HttpStatus status, String message) {
       return ApiError.builder()
               .apiPath(apiPath)
               .status(status.name())
               .code(status.value())
               .errorTime(LocalDateTime.now())
               .body(new MessageBody(message)) 
               .build();    
    }

    // public static ApiError createWithDetails(String apiPath, String status, Integer code, Map<String, String> details) {
    //     return ApiError.builder()
    //             .apiPath(apiPath)
    //             .status(status)
    //             .code(code)
    //             .errorTime(LocalDateTime.now())
    //             .body(new DetailsBody(details)) 
    //             .build();
    // }


    public static ApiError createWithDetails(String apiPath, HttpStatus status, Map<String, String> details) {
        return ApiError.builder()
                .apiPath(apiPath)
                .status(status.name())
                .code(status.value())
                .errorTime(LocalDateTime.now())
                .body(new DetailsBody(details)) 
                .build();
    }
}
