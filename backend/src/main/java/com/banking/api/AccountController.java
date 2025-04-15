package com.banking.api;

import java.util.Random;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.banking.database.DatabaseAccountManager;
import com.banking.models.Account;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final DatabaseAccountManager dbManager = new DatabaseAccountManager();
    private final Random random = new Random();

    @PostMapping("/create")
    public @ResponseBody Object createAccount(@RequestBody AccountRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();

        // Generate a unique 8-digit account number
        String accountNumber;
        do {
            accountNumber = String.format("%12d", random.nextInt(100_000_000));
        } while (dbManager.accountExists(accountNumber));

        // Create and store the account
        Account newAccount = new Account(accountNumber, email, email, 0.0); // Using email as name placeholder
        newAccount.setPassword(password); // Add setPassword method in Account if needed

        boolean success = dbManager.createAccount(newAccount);
        if (success) {
            return new AccountResponse(accountNumber, "Account created successfully.");
        } else {
            return new ErrorResponse("Account creation failed.");
        }
    }

    // Helper classes for request and response
    public static class AccountRequest {
        private String email;
        private String password;
        public String getEmail() { return email; }
        public String getPassword() { return password; }
        public void setEmail(String email) { this.email = email; }
        public void setPassword(String password) { this.password = password; }
    }

    static class AccountResponse {
        private final String accountNumber;
        private final String message;
        public AccountResponse(String accountNumber, String message) {
            this.accountNumber = accountNumber;
            this.message = message;
        }
        public String getAccountNumber() { return accountNumber; }
        public String getMessage() { return message; }
    }

    static class ErrorResponse {
        private final String message;
        public ErrorResponse(String message) {
            this.message = message;
        }
        public String getMessage() { return message; }
    }
}
