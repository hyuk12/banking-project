package com.example.bankingproject.service;

import com.example.bankingproject.controller.dto.FriendDto;
import com.example.bankingproject.domain.Account;
import com.example.bankingproject.domain.User;
import com.example.bankingproject.repository.AccountRepository;
import com.example.bankingproject.repository.FriendRepository;
import com.example.bankingproject.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
class BankServiceTest {
    @Autowired
    private BankService bankService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private FriendRepository friendRepository;

    private User user1;
    private User user2;

    @BeforeEach
    public void setup() {
        user1 = new User("user1", "password1");
        user2 = new User("user2", "password2");

        bankService.register(user1);
        bankService.register(user2);

        FriendDto friendDTO = new FriendDto(user2.getId());
        bankService.addFriend(user1.getId(), friendDTO);
    }

    @Test
    public void testTransfer() {
        BigDecimal initialAmount = new BigDecimal(100);
        BigDecimal transferAmount = new BigDecimal(50);

        Account fromAccount = accountRepository.findById(user1.getId()).orElse(null);
        fromAccount.setBalance(initialAmount);
        accountRepository.save(fromAccount);

        boolean result = bankService.transfer(user1.getId(), user2.getId(), transferAmount);
        assertTrue(result);

        Account updatedFromAccount = accountRepository.findById(user1.getId()).orElse(null);
        Account updatedToAccount = accountRepository.findById(user2.getId()).orElse(null);

        assertEquals(initialAmount.subtract(transferAmount), updatedFromAccount.getBalance());
        assertEquals(transferAmount, updatedToAccount.getBalance());
    }

    @Test
    public void testNotFriendTransfer() {
        User user3 = new User("user3", "password3");
        bankService.register(user3);

        BigDecimal initialAmount = new BigDecimal(100);
        BigDecimal transferAmount = new BigDecimal(50);

        Account fromAccount = accountRepository.findById(user1.getId()).orElse(null);
        fromAccount.setBalance(initialAmount);
        accountRepository.save(fromAccount);

        boolean result = bankService.transfer(user1.getId(), user3.getId(), transferAmount);
        assertFalse(result);

        Account updatedFromAccount = accountRepository.findById(user1.getId()).orElse(null);
        Account updatedToAccount = accountRepository.findById(user3.getId()).orElse(null);

        assertEquals(initialAmount, updatedFromAccount.getBalance());
        assertEquals(BigDecimal.ZERO, updatedToAccount.getBalance());
    }

    @Test
    public void testNotEnoughBalanceTransfer() {
        BigDecimal initialAmount = new BigDecimal(100);
        BigDecimal transferAmount = new BigDecimal(150);
        Account fromAccount = accountRepository.findById(user1.getId()).orElse(null);
        fromAccount.setBalance(initialAmount);
        accountRepository.save(fromAccount);

        boolean result = bankService.transfer(user1.getId(), user2.getId(), transferAmount);
        assertFalse(result);
        Account transferredFromAccount = accountRepository.findById(user1.getId()).orElse(null);
        assertEquals(initialAmount, transferredFromAccount.getBalance());
    }

}
