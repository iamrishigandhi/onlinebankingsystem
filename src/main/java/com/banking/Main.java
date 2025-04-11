package com.banking;

import com.banking.models.AccountManager;

public class Main {
    public static void main(String[] args) {
        AccountManager manager = new AccountManager();

        // Create some accounts
        manager.createAccount("1001", "Alice Smith", "alice@example.com", 500.0);
        manager.createAccount("1002", "Bob Johnson", "bob@example.com", 1000.0);

        // Deposit money
        manager.depositToAccount("1001", 200.0);

        // Withdraw money
        manager.withdrawFromAccount("1002", 300.0);

        // Display all accounts
        manager.displayAllAccounts();
        System.out.println("It works!");
    }
}
