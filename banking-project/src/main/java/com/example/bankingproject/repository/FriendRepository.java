package com.example.bankingproject.repository;

import com.example.bankingproject.domain.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
    List<Friend> findAllByUserId(Long userId);

    Optional<Friend> findByUserIdAndFriendId(Long userId, Long friendId);

    void deleteByUserIdAndFriendId(Long userId, Long friendId);
}
