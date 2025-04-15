package com.banking.service;

import java.util.HashMap;
import java.util.Map;

import com.banking.models.Account;
import com.banking.models.AccountCreationResult;

public class Bank {
    private final Map<String, Account> accounts = new HashMap<>();

    // Create a new account
    public AccountCreationResult createAccount(String number, String name, String email, double initialDeposit) {
        if (!isValidAccountNumber(number)) {
            return new AccountCreationResult(false, "Account number must be exactly 8 digits and contain only numbers.");
        }

        if (accounts.containsKey(number)) {
            return new AccountCreationResult(false, "Account with this number already exists.");
        }

        // Create and store the new account
        accounts.put(number, new Account(number, name, email, initialDeposit));
        return new AccountCreationResult(true, "Account created successfully.");
    }

    // Account number validation method
    private boolean isValidAccountNumber(String number) {
        return number.matches("\\d{8}");
    }

    // Deposit money into an account
    public boolean deposit(String number, double amount) {
        Account acc = accounts.get(number);
        if (acc != null && amount > 0) {
            acc.deposit(amount);
            return true;
        }
        return false;
    }

    // Get an account by its number
    public Account getAccount(String number) {
        return accounts.get(number);
    }

    // Get all accounts
    public Map<String, Account> getAllAccounts() {
        return accounts;
    }
}
