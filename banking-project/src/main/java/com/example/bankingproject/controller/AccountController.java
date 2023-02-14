package com.example.bankingproject.controller;

import com.example.bankingproject.controller.dto.AccountDto;
import com.example.bankingproject.service.AccountCreationException;
import com.example.bankingproject.service.AccountService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/accounts")
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto requestDto) throws AccountCreationException {
        AccountDto accountDto = accountService.createAccount(requestDto.getUserId());
        return ResponseEntity.ok(accountDto);
    }
}
