package com.example.bankingproject.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false, unique = true)
    private String accountNumber;


    public Account(User user, String accountNumber) {
        this.user = user;
        this.accountNumber = accountNumber;
    }

}
