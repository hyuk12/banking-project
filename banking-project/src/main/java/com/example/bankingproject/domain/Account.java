package com.example.bankingproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@Getter @Setter
public class Account {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private Long userId;
        private BigDecimal balance;

        public Account() {
        }

        public Account(Long userId, BigDecimal balance) {
                this.userId = userId;
                this.balance = balance;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public Long getUserId() {
                return userId;
        }

        public void setUserId(Long userId) {
                this.userId = userId;
        }

        public BigDecimal getBalance() {
                return balance;
        }

        public void setBalance(BigDecimal balance) {
                this.balance = balance;
        }
}
