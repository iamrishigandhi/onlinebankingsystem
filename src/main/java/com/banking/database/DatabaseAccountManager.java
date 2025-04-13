package com.banking.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public Account getAccount(String accountNumber) {
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
            }

        } catch (SQLException e) {
            System.out.println("Error fetching account: " + e.getMessage());
        }

        return null;
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
}
