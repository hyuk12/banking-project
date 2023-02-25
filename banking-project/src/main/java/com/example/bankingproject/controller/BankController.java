package com.example.bankingproject.controller;

import com.example.bankingproject.controller.dto.FriendDto;
import com.example.bankingproject.domain.Account;
import com.example.bankingproject.domain.User;
import com.example.bankingproject.service.AlertService;

import com.example.bankingproject.service.BankService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class BankController {
    private BankService bankService;
    private AlertService alertService;

    public BankController(BankService bankService, AlertService alertService) {
        this.bankService = bankService;
        this.alertService = alertService;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return bankService.register(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        return bankService.login(user);
    }

    @GetMapping("/friends")
    public List<FriendDto> getFriends(@RequestParam Long userId) {
        return bankService.getFriends(userId);
    }

    @PostMapping("/friends")
    public FriendDto addFriend(@RequestParam Long userId, @RequestBody FriendDto friendDTO) {
        return bankService.addFriend(userId, friendDTO);
    }

    @GetMapping("/account")
    public Account getAccount(@RequestParam Long userId) {
        return bankService.getAccount(userId);
    }

    @PostMapping("/transfer")
    public void transfer(@RequestParam Long fromUserId, @RequestParam Long toUserId,
                         @RequestParam BigDecimal amount) {

        if (!bankService.isFriend(fromUserId, toUserId)) {
            alertService.sendTransferFailedAlert(fromUserId, "The recipient is not your friend.");
            return;
        }

        boolean success = bankService.transfer(fromUserId, toUserId, amount);
        if (success) {
            alertService.sendTransferSuccessAlert(fromUserId, amount);
            alertService.sendTransferSuccessAlert(toUserId, amount);
        } else {
            alertService.sendTransferFailedAlert(fromUserId, "failed");
        }
    }
}

