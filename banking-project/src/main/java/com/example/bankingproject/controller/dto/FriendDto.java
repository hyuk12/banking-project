package com.example.bankingproject.controller.dto;

import com.example.bankingproject.domain.Friend;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class FriendDto {
    private Long friendId;

    public FriendDto() {
    }

    public FriendDto(Friend friend) {
    }

    public FriendDto(long l, String friend1) {
    }
}
