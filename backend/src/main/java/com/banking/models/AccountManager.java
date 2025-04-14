package com.banking.models;

import java.util.HashMap;

import com.banking.exceptions.InsufficientFundsException;
import com.banking.exceptions.InvalidAccountNumberException;

public class AccountManager {
    private final HashMap<String, Account> accounts;

    public AccountManager() {
        accounts = new HashMap<>();
    }

    public void createAccount(String accountNumber, String name, String email, double initialBalance) {
        try {
            validateAccountNumber(accountNumber);

            if (!accounts.containsKey(accountNumber)) {
                Account newAccount = new Account(accountNumber, name, email, initialBalance);
                accounts.put(accountNumber, newAccount);
                System.out.println("Account created successfully.");
            } else {
                System.out.println("Account with this number already exists.");
            }

        } catch (InvalidAccountNumberException e) {
            System.out.println(e.getMessage());
        }
    }

    private void validateAccountNumber(String accountNumber) throws InvalidAccountNumberException {
        if (!accountNumber.matches("\\d{8}")) {
            throw new InvalidAccountNumberException("Account number must be exactly 8 digits (numbers only).");
        }
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            for (Account acc : accounts.values()) {
                System.out.println(acc);
            }
        }
    }

    public boolean depositToAccount(String accountNumber, double amount) {
        Account acc = getAccount(accountNumber);
        if (acc != null) {
            acc.deposit(amount);
            return true;
        }
        return false;
    }

    public boolean withdrawFromAccount(String accountNumber, double amount) {
        Account acc = getAccount(accountNumber);
        if (acc != null) {
            try {
                return acc.withdraw(amount);
            } catch (InsufficientFundsException e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }
}
