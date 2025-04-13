package com.banking.models;

public class AccountCreationResult {
    private final boolean success;
    private final String message;

    public AccountCreationResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
