package com.banking;

import java.util.HashMap;
import java.util.Map;

import com.banking.models.Account;
import com.banking.models.InsufficientFundsException;

public class Bank {
    private final Map<String, Account> accounts = new HashMap<>();

    // Create a new account
    public boolean createAccount(String number, String name, String email, double initialDeposit) {
        if (accounts.containsKey(number)) return false;  // Check if account exists
        accounts.put(number, new Account(number, name, email, initialDeposit));
        return true;
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

    // Withdraw money from an account
    public boolean withdraw(String number, double amount) {
        Account acc = accounts.get(number);
        if (acc != null && amount > 0) {
            try {
                return acc.withdraw(amount);
            } catch (InsufficientFundsException e) {
                System.out.println("Error: " + e.getMessage());
            }
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