package com.eazybites.accounts.exception.error.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseErrorResponse implements Serializable {
    private String apiPath;
    private String status;
    private Integer code;
    private LocalDateTime errorTime;
}
