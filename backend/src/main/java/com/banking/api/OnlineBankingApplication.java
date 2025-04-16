package com.banking.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.banking")
public class OnlineBankingApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineBankingApplication.class, args);
    }
}
