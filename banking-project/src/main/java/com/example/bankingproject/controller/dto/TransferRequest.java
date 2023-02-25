package com.example.bankingproject.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter @Setter
public class TransferRequest {
    private Long fromUserId;
    private Long toUserId;
    private BigDecimal amount;
}
