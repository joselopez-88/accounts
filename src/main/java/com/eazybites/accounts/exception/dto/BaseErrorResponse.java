package com.eazybites.accounts.exception.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

// import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@AllArgsConstructor
@NoArgsConstructor
// @Schema(description = "Base Error Response")
public abstract class BaseErrorResponse implements Serializable {
    private String apiPath;
    private String status;
    private Integer code;
    private LocalDateTime errorTime;
}
