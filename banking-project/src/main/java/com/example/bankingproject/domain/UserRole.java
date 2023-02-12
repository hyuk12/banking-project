package com.example.bankingproject.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "user_role")
public class UserRole {

    @Id
    @Column(name = "user_user_id")
    private String user_id;

    private String role;


}
