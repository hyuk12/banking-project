package com.example.bankingproject.service;

import com.example.bankingproject.domain.User;
import com.example.bankingproject.repository.UserRepository;
import com.mysql.cj.exceptions.PasswordExpiredException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User addUser(String username, String password) {
        if (username == null || username.isEmpty()) {
            throw new ConstraintViolationException("Username should not be empty", null);
        }
        if (userRepository.findByUsername(username).isPresent()) {
            throw new ConstraintViolationException("Username already exists", null);
        }
        User user = new User(username, password);
        return userRepository.save(user);
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found with username: " + username));
    }

    public User login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found with username: " + username));
        if (!user.getPassword().equals(password)) {
            throw new PasswordExpiredException("Invalid password for user with username: " + username);
        }
        return user;
    }
}
