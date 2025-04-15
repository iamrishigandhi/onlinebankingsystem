package com.banking.api.dto;

public class CreateAccountRequest {
    private String email;
    private String password;

    // Required for Jackson
    public CreateAccountRequest() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}