package com.banking.api.dto;

public class CreateAccountResponse {
    private final String accountNumber;
    private final String message;

    public CreateAccountResponse(String accountNumber, String message) {
        this.accountNumber = accountNumber;
        this.message = message;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getMessage() {
        return message;
    }
}