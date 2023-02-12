package com.example.bankingproject.repository;

import com.example.bankingproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    User findByPassword(String password);


    Optional<User> findByUsername(String username);
}
