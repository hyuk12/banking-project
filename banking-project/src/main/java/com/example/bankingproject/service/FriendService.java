package com.example.bankingproject.service;

import com.example.bankingproject.domain.Friend;
import com.example.bankingproject.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FriendService {
    private final FriendRepository friendRepository;

    public List<Friend> getFriends(Long userId) {
        return friendRepository.findAllByUserId(userId);
    }

    public boolean isFriend(Long userId, Long friendId) {
        return friendRepository.findByUserIdAndFriendId(userId, friendId).isPresent();
    }

    @Transactional
    public void addFriend(Long userId, Long friendId) {
        Friend friend = new Friend(userId, friendId);
        friendRepository.save(friend);
    }

    @Transactional
    public void removeFriend(Long userId, Long friendId) {
        friendRepository.deleteByUserIdAndFriendId(userId, friendId);
    }
}
