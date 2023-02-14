package com.example.bankingproject.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "friends")
@NoArgsConstructor
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "friend_id")
    private Long friendId;

    public Friend(Long userId, Long friendId) {
        this.userId = userId;
        this.friendId = friendId;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }
}
