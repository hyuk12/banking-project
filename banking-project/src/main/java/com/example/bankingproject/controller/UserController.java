package com.example.bankingproject.controller;


import com.example.bankingproject.dto.jwt.TokenInfo;
import com.example.bankingproject.dto.user.request.UserCreateRequest;
import com.example.bankingproject.dto.user.request.UserLoginRequestDto;
import com.example.bankingproject.service.UserService;
import com.example.bankingproject.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;



    @PostMapping("/login")
    public TokenInfo login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        String username = userLoginRequestDto.getUsername();
        String password = userLoginRequestDto.getPassword();
        TokenInfo tokenInfo = userService.login(username, password);
        return tokenInfo;
    }

    @PostMapping("/test")
    public String test() {
        return "success";
    }

}
