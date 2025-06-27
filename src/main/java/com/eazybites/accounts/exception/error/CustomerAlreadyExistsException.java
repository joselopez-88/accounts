package com.eazybites.accounts.exception.error;

public class CustomerAlreadyExistsException extends RuntimeException {
    private static final String message = "Customer already registered with given mobileNumber %s";
    public CustomerAlreadyExistsException(String mobileNumber) {
        super(String.format(message, mobileNumber));
    }

}
