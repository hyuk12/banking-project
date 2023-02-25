package com.example.bankingproject.repository;

import com.example.bankingproject.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
