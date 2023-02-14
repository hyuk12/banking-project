package com.example.bankingproject.repository;

import com.example.bankingproject.domain.Account;
import com.example.bankingproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByUser(User user);

    Optional<Object> findByUser(User user);
}
