package com.example.bankingproject.controller.dto;

import com.example.bankingproject.domain.Account;
import com.example.bankingproject.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class AccountDto {
    private Long id;
    private String accountNumber;
    private BigDecimal balance;
    private Long userId;

    public static AccountDto fromEntity(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getUserId());
        accountDto.setBalance(account.getBalance());
        return accountDto;
    }

    // getters and setters
}
