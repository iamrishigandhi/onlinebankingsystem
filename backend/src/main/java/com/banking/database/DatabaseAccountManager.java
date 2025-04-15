package com.banking.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.banking.exceptions.InvalidAccountNumberException;
import com.banking.models.Account;
import com.banking.utils.DBUtil;

public class DatabaseAccountManager {

    public boolean createAccount(Account account) {
        String sql = "INSERT INTO accounts (account_number, holder_name, email, balance) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, account.getAccountNumber());
            stmt.setString(2, account.getAccountHolderName());
            stmt.setString(3, account.getEmail());
            stmt.setDouble(4, account.getBalance());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error creating account: " + e.getMessage());
            return false;
        }
    }

    public Account getAccount(String accountNumber) throws InvalidAccountNumberException {
        validateAccountNumber(accountNumber);

        String sql = "SELECT * FROM accounts WHERE account_number = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, accountNumber);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Account(
                    rs.getString("account_number"),
                    rs.getString("holder_name"),
                    rs.getString("email"),
                    rs.getDouble("balance")
                );
            } else {
                throw new InvalidAccountNumberException("Account number " + accountNumber + " is invalid.");
            }

        } catch (SQLException e) {
            System.out.println("Error fetching account: " + e.getMessage());
            throw new InvalidAccountNumberException("Database error occurred while fetching account.");
        }
    }

    public boolean updateBalance(String accountNumber, double newBalance) {
        String sql = "UPDATE accounts SET balance = ? WHERE account_number = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, newBalance);
            stmt.setString(2, accountNumber);
            int rows = stmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error updating balance: " + e.getMessage());
            return false;
        }
    }

    public boolean accountExists(String accountNumber) {
        String sql = "SELECT 1 FROM accounts WHERE account_number = ?";
    
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, accountNumber);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Error checking account existence: " + e.getMessage());
            return false;
        }
    }
    
    public void validateAccountNumber(String accountNumber) throws InvalidAccountNumberException {
        StringBuilder errorMsg = new StringBuilder();

        if (accountNumber == null || accountNumber.isEmpty()) {
            errorMsg.append("Account number cannot be empty.\n");
        }

        if (accountNumber != null && !accountNumber.matches("\\d+")) {
            errorMsg.append("Account number must contain only numeric characters.\n");
        }

        if (accountNumber != null && accountNumber.length() < 8) {
            errorMsg.append("Account number must be at least 8 digits long.\n");
        }

        if (errorMsg.length() > 0) {
            throw new InvalidAccountNumberException(errorMsg.toString());
        }
    }
}