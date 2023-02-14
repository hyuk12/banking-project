package com.example.bankingproject.controller.dto;

import com.example.bankingproject.domain.Account;
import com.example.bankingproject.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AccountDto {
    private Long id;
    private String accountNumber;
    private Long userId;

    public static AccountDto fromEntity(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setUserId(account.getUser().getId());
        return accountDto;
    }

    // getters and setters
}
