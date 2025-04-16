package com.banking.api.response;

public class AccountResponse {
    private String accountNumber;
    private String message;

    public AccountResponse() {}

    public AccountResponse(String accountNumber, String message) {
        this.accountNumber = accountNumber;
        this.message = message;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
