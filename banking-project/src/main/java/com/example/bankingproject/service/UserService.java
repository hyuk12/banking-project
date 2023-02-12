package com.example.bankingproject.service;

import com.example.bankingproject.dto.jwt.TokenInfo;
import com.example.bankingproject.dto.user.request.UserCreateRequest;

public interface UserService {


    public TokenInfo login(String username, String password);
}
