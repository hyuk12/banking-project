package com.example.bankingproject.controller;

import com.example.bankingproject.domain.Friend;
import com.example.bankingproject.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FriendController {

    private final FriendService friendService;

    @GetMapping("/friends/{userId}")
    public ResponseEntity<List<Friend>> getFriends(@PathVariable Long userId) {
        List<Friend> friends = friendService.getFriends(userId);
        return ResponseEntity.ok(friends);
    }

    @GetMapping("/friends/{userId}/is-friend/{friendId}")
    public ResponseEntity<Boolean> isFriend(@PathVariable Long userId, @PathVariable Long friendId) {
        boolean isFriend = friendService.isFriend(userId, friendId);
        return ResponseEntity.ok(isFriend);
    }

    @PostMapping("/friends/{userId}/add-friend/{friendId}")
    public ResponseEntity<?> addFriend(@PathVariable Long userId, @PathVariable Long friendId) {
        friendService.addFriend(userId, friendId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/friends/{userId}/remove-friend/{friendId}")
    public ResponseEntity<?> removeFriend(@PathVariable Long userId, @PathVariable Long friendId) {
        friendService.removeFriend(userId, friendId);
        return ResponseEntity.noContent().build();
    }
}
