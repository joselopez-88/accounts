package com.eazybites.accounts.exception.advice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.eazybites.accounts.exception.dto.BaseErrorResponse;
import com.eazybites.accounts.exception.dto.ErrorResponse;
import com.eazybites.accounts.exception.dto.ErrorsResponse;
import com.eazybites.accounts.exception.error.CustomerAlreadyExistsException;
import com.eazybites.accounts.exception.error.ResourceNotFoundException;

// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.media.Content;
// import io.swagger.v3.oas.annotations.media.Schema;
// import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // @Operation(summary = "Handle Generic Exception")
    // @ApiResponse(
    // responseCode = "500",
    // description = "Internal Server Error",
    // content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    // )
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

    // @Operation(summary = "Handle Customer Already Exists Exception")
    // @ApiResponse(
    // responseCode = "400",
    // description = "Bad Request",
    // content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    // )
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

    // @Operation(summary = "Handle Resource Not Found Exception")
    // @ApiResponse(
    // responseCode = "404",
    // description = "Not Found",
    // content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    // )
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

     
/**
 * Handles MethodArgumentNotValidException thrown when a request with invalid
 * method arguments is received. This method extracts field errors from the
 * exception, maps them into a key-value pair of field name and error message,
 * and returns an ErrorsResponse containing the error details.
 *
 * @param exception the exception containing validation errors in the request body
 * @param webRequest the web request during which the exception occurred
 * @return an ErrorsResponse object with detailed error information
 */
    // @Operation(summary = "Handle Method Argument Not Valid Exception")
    // @ApiResponse(
    //     responseCode = "400",
    //     description = "Bad Request",
    //     content = @Content(schema = @Schema(implementation = ErrorsResponse.class))
    // )
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseErrorResponse handlerMalFormedRequest(MethodArgumentNotValidException exception, WebRequest webRequest) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
     
        return ErrorsResponse.builder()
            .apiPath(webRequest.getDescription(false))
            .status(HttpStatus.BAD_REQUEST.name())
            .code(HttpStatus.BAD_REQUEST.value())
            .errorTime(LocalDateTime.now())
            .errors(errors)
            .build();
    }


    /**
     * Handles MethodArgumentTypeMismatchException thrown when a request with invalid
     * argument type is received. This method extracts the error message from the
     * exception, maps it into an ErrorResponse containing the error details, and
     * returns the ErrorResponse object.
     *
     * @param exception the exception containing type mismatch errors in the request body
     * @param webRequest the web request during which the exception occurred
     * @return an ErrorResponse object with detailed error information
     */
    // @Operation(summary = "Handle Method Argument Type Mismatch Exception")
    // @ApiResponse(
    //     responseCode = "400",
    //     description = "Bad Request",
    //     content = @Content(schema = @Schema(implementation = ErrorResponse.class))
    // )
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public BaseErrorResponse handleTypeMismatch(MethodArgumentTypeMismatchException exception, WebRequest webRequest) {
            return ErrorResponse.builder()
            .apiPath(webRequest.getDescription(false))
            .status(HttpStatus.BAD_REQUEST.name())            
            .code(HttpStatus.BAD_REQUEST.value())
            .errorTime(LocalDateTime.now())
            .message(exception.getMessage())
            .build();   
    }
    /**
     * Handles ConstraintViolationException thrown when a request with invalid
     * path and request parameters is received. This method extracts the error
     * message from the exception, maps it into an ErrorResponse containing the
     * error details, and returns the ErrorResponse object.
     *
     * @param exception the exception containing validation errors in the request
     *                  path and request parameters
     * @param webRequest the web request during which the exception occurred
     * @return an ErrorResponse object with detailed error information
     */
    // @Operation(summary = "Handle Constraint Violation Exception")
    // @ApiResponse(
    //     responseCode = "400",
    //     description = "Bad Request",
    //     content = @Content(schema = @Schema(implementation = ErrorsResponse.class))
    // )
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public BaseErrorResponse handlerPathAndRequestParam(ConstraintViolationException exception, WebRequest webRequest) {
        Map<String, String> errors = new HashMap<>();
        exception.getConstraintViolations().forEach(violation -> {
            String path = violation.getPropertyPath().toString();
            // Extrae el nombre del parámetro (última parte del path)
            String fieldName = path.substring(path.lastIndexOf('.') + 1);
            errors.put(fieldName, violation.getMessage());
        });
            return ErrorsResponse.builder()
            .apiPath(webRequest.getDescription(false))
            .status(HttpStatus.BAD_REQUEST.name())            
            .code(HttpStatus.BAD_REQUEST.value())
            .errorTime(LocalDateTime.now())
            .errors(errors)
            .build();   
    }
}
