package com.example.bankingproject.controller;

import com.example.bankingproject.domain.User;
import com.example.bankingproject.dto.user.request.UserCreateRequest;
import com.example.bankingproject.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public void saveUser(@RequestParam UserCreateRequest request) {
        userService.saveUser(request);
    }

    @GetMapping("/user/friends")
    public void saveFriends() {

    }


}
