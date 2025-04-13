package com.banking.ui;

import com.banking.database.DatabaseAccountManager;
import com.banking.exceptions.InsufficientFundsException;
import com.banking.exceptions.InvalidAccountNumberException;
import com.banking.models.Account;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BankingApp extends Application {

    private final DatabaseAccountManager dbManager = new DatabaseAccountManager();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Online Banking System");

        // Fields
        TextField accNumField = new TextField();
        accNumField.setPromptText("Account Number");

        TextField nameField = new TextField();
        nameField.setPromptText("Account Holder Name");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        TextField amountField = new TextField();
        amountField.setPromptText("Amount");

        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setPrefHeight(200);

        // Buttons
        Button createBtn = new Button("Create Account");
        createBtn.setOnAction(e -> {
            String num = accNumField.getText();
            String name = nameField.getText();
            String email = emailField.getText();
            double amount;
            try {
                amount = Double.parseDouble(amountField.getText());

                // Create Account in the Database
                Account account = new Account(num, name, email, amount);
                boolean success = dbManager.createAccount(account);
                if (success) {
                    outputArea.appendText("Account created successfully.\n");
                } else {
                    outputArea.appendText("Account creation failed. The account already exists.\n");
                }

            } catch (NumberFormatException ex) {
                outputArea.appendText("Invalid deposit amount.\n");
            }
        });

        Button depositBtn = new Button("Deposit");
        depositBtn.setOnAction(e -> {
            String num = accNumField.getText();
            try {
                double amount = Double.parseDouble(amountField.getText());

                Account acc = dbManager.getAccount(num);
                acc.deposit(amount);
                dbManager.updateBalance(num, acc.getBalance());
                outputArea.appendText("Deposit successful. New Balance: " + acc.getBalance() + "\n");
                amountField.clear();

            } catch (NumberFormatException ex) {
                outputArea.appendText("Invalid amount.\n");
            } catch (InvalidAccountNumberException ex) {
                outputArea.appendText("Error: " + ex.getMessage() + "\n");
            }
        });

        Button withdrawBtn = new Button("Withdraw");
        withdrawBtn.setOnAction(e -> {
            String num = accNumField.getText();
            try {
                double amount = Double.parseDouble(amountField.getText());
        
                Account acc = dbManager.getAccount(num);  // May throw
                try {
                    acc.withdraw(amount);
                    dbManager.updateBalance(num, acc.getBalance());
                    outputArea.appendText("Withdrawal successful. New Balance: " + acc.getBalance() + "\n");
                } catch (InsufficientFundsException ex) {
                    outputArea.appendText("Error: " + ex.getMessage() + "\n");
                }
        
                amountField.clear();
        
            } catch (NumberFormatException ex) {
                outputArea.appendText("Invalid amount.\n");
            } catch (InvalidAccountNumberException ex) {
                outputArea.appendText("Error: " + ex.getMessage() + "\n");
            }
        });

        Button viewBtn = new Button("View Account");
        viewBtn.setOnAction(e -> {
            String num = accNumField.getText();
            try {
                Account acc = dbManager.getAccount(num);  // May throw
                outputArea.appendText(acc.toString() + "\n");
            } catch (InvalidAccountNumberException ex) {
                outputArea.appendText("Error: " + ex.getMessage() + "\n");
            }
        });

        Button clearBtn = new Button("Clear All");
        clearBtn.setOnAction(e -> {
            accNumField.clear();
            nameField.clear();
            emailField.clear();
            amountField.clear();
        });

        // Layout
        VBox layout = new VBox(10,
                accNumField, nameField, emailField, amountField,
                new HBox(10, createBtn, depositBtn, withdrawBtn, viewBtn, clearBtn),
                outputArea
        );
        layout.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(layout, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
