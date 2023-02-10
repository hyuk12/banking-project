package com.example.bankingproject.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45)
    private String username;

    @Column(nullable = false)
    private String password;

    public User(String username, String password) {
        if(username == null || username.isBlank() || password == null || password.isBlank()) {
            throw new IllegalArgumentException("회원가입 실패");
        }
        this.username = username;
        this.password = password;
    }

}
