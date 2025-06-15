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
import com.eazybites.accounts.exception.error.ResourceNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public BaseErrorResponse handlerGlobalException(Exception exception, WebRequest webRequest) {
        return ErrorResponse.builder()
            .apiPath(webRequest.getDescription(false))
            .status(HttpStatus.INTERNAL_SERVER_ERROR.name())
            .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .errorTime(LocalDateTime.now())
            .message(exception.getMessage())
            .build();
    }

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

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public BaseErrorResponse handlerResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {
        return ErrorResponse.builder()
            .apiPath(webRequest.getDescription(false))
            .status(HttpStatus.NOT_FOUND.name())
            .code(HttpStatus.NOT_FOUND.value())
            .errorTime(LocalDateTime.now())
            .message(exception.getMessage())
            .build();
    }
}
