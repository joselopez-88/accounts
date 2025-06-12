package com.eazybites.accounts.exception.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseErrorResponse implements Serializable {
    private String apiPath;
    private String status;
    private Integer code;
    private LocalDateTime errorTime;
}
