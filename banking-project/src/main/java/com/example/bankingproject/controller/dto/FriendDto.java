package com.example.bankingproject.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FriendDto {
    private Long userId;
    private Long friendId;
}
