package com.banking.models;

public class Account {
    private final String accountNumber;
    private final String accountHolderName;
    private final String email;
    private double balance;

    public Account(String accountNumber, String accountHolderName, String email, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.email = email;
        this.balance = balance;
    }

    // Getters and setters
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getEmail() {
        return email;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            System.out.println("Error: Withdrawal amount must be greater than zero.");
            return false;
        }
        if (balance < amount) {
            throw new InsufficientFundsException("Error: Cannot withdraw more than the balance. Your balance is " + balance);
        }
        balance -= amount;
        return true;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", accountHolderName='" + accountHolderName + '\'' +
                ", email='" + email + '\'' +
                ", balance=" + balance +
                '}';
    }
}