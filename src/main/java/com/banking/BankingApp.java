package com.banking;

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

    private final Bank bank = new Bank();

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
                boolean success = bank.createAccount(num, name, email, amount);
                outputArea.appendText(success ? "Account created.\n" : "Account already exists.\n");
            } catch (NumberFormatException ex) {
                outputArea.appendText("Invalid deposit amount.\n");
            }
        });

        Button depositBtn = new Button("Deposit");
        depositBtn.setOnAction(e -> {
            String num = accNumField.getText();
            try {
                double amount = Double.parseDouble(amountField.getText());
                boolean success = bank.deposit(num, amount);
                outputArea.appendText(success ? "Deposit successful.\n" : "Deposit failed.\n");
            } catch (NumberFormatException ex) {
                outputArea.appendText("Invalid amount.\n");
            }
        });

        Button withdrawBtn = new Button("Withdraw");
        withdrawBtn.setOnAction(e -> {
            String num = accNumField.getText();
            try {
                double amount = Double.parseDouble(amountField.getText());
                boolean success = bank.withdraw(num, amount);
                outputArea.appendText(success ? "Withdrawal successful.\n" : "Withdrawal failed. Insufficient funds. Please check your balance and try again.\n");
            } catch (NumberFormatException ex) {
                outputArea.appendText("Invalid amount.\n");
            }
        });

        Button viewBtn = new Button("View Account");
        viewBtn.setOnAction(e -> {
            String num = accNumField.getText();
            Account acc = bank.getAccount(num);
            if (acc != null) {
                outputArea.appendText(acc.toString() + "\n");
            } else {
                outputArea.appendText("Account not found.\n");
            }
        });

        // Layout
        VBox layout = new VBox(10,
                accNumField, nameField, emailField, amountField,
                new HBox(10, createBtn, depositBtn, withdrawBtn, viewBtn),
                outputArea
        );
        layout.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(layout, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}