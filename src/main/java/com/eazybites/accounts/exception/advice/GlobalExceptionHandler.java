package com.eazybites.accounts.exception.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.eazybites.accounts.exception.dto.BaseErrorResponse;
import com.eazybites.accounts.exception.dto.ErrorResponse;
import com.eazybites.accounts.exception.error.CustomerAlreadyExistsException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public BaseErrorResponse handlerCustomerMobileNumberExists(CustomerAlreadyExistsException exception, WebRequest webRequest) {
        return ErrorResponse.builder()
            .apiPath(webRequest.getDescription(false))
            .status(HttpStatus.BAD_REQUEST.name())
            .code(HttpStatus.BAD_REQUEST.value())
            .errorTime(LocalDateTime.now())
            .message(exception.getMessage())
            .build();
    }
}
