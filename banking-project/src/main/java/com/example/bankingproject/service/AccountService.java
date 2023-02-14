package com.example.bankingproject.service;

import com.example.bankingproject.controller.dto.AccountDto;
import com.example.bankingproject.domain.Account;
import com.example.bankingproject.domain.User;
import com.example.bankingproject.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final UserService userService;


    public AccountDto createAccount(Long userId) throws AccountCreationException {
        User user  = userService.getUser(userId);
        if (accountRepository.existsByUser(user)) {
            throw new AccountCreationException("Account already exists for the user");
        }

        String accountNumber = generateAccountNumber();

        Account account = new Account(user, accountNumber);
        Account savedAccount = accountRepository.save(account);
        return AccountDto.fromEntity(savedAccount);
    }

    private String generateAccountNumber() {
        String year = String.valueOf(LocalDate.now().getYear()).substring(2);
        String randomDigits = String.format("%010d", new Random().nextInt(1000000000));
        String accountNumber = year + randomDigits;

        return accountNumber.substring(0, 6) + "-" +
                accountNumber.substring(6, 8) + "-" +
                accountNumber.substring(8, 11) + "-" +
                accountNumber.substring(11, 13);
    }

    public void addAccount(User user) {
    }
}
