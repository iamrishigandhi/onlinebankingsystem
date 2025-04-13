package com.banking.exceptions;

public class InvalidAccountNumberException extends Exception {
    public InvalidAccountNumberException(String message) {
        super(message);
    }
}
