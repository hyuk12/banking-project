package com.example.bankingproject.service;

import com.example.bankingproject.domain.User;
import com.example.bankingproject.dto.user.request.UserCreateRequest;
import com.example.bankingproject.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(UserCreateRequest request) {
        userRepository.save(new User(request.getUsername(), request.getPassword()));
    }
}
