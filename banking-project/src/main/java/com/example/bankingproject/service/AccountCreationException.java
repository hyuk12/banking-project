package com.example.bankingproject.service;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;


public class AccountCreationException extends RuntimeException {
    public AccountCreationException(String message) {
        super(message);
    }
}
