package com.example.bankingproject.service;

import com.example.bankingproject.controller.dto.FriendDto;
import com.example.bankingproject.domain.Account;
import com.example.bankingproject.domain.Friend;
import com.example.bankingproject.domain.User;
import com.example.bankingproject.repository.AccountRepository;
import com.example.bankingproject.repository.FriendRepository;
import com.example.bankingproject.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankService {
    private UserRepository userRepository;
    private AccountRepository accountRepository;
    private FriendRepository friendRepository;

    public BankService(UserRepository userRepository, AccountRepository accountRepository,
                       FriendRepository friendRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.friendRepository = friendRepository;
    }

    public User register(User user) {
        userRepository.save(user);
        accountRepository.save(new Account(user.getId(), BigDecimal.ZERO));
        return user;
    }

    public User login(User user) {
        User loginUser = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (loginUser == null) {
            throw new IllegalArgumentException("일치하는 사용자가 없습니다.");
        }
        return loginUser;
    }

    public List<FriendDto> getFriends(Long userId) {
        List<Friend> friends = friendRepository.findByUserId(userId);
        return friends.stream().map(FriendDto::new).collect(Collectors.toList());
    }

    public FriendDto addFriend(Long userId, FriendDto friendDTO) {
        Friend friend = new Friend(userId, friendDTO.getFriendId());
        friendRepository.save(friend);
        return friendDTO;
    }

    public Account getAccount(Long userId) {
        return accountRepository.findById(userId).orElse(null);
    }

    public boolean isFriend(Long fromUserId, Long toUserId) {
        // Check if fromUserId and toUserId are friends
        List<FriendDto> friends = getFriends(fromUserId);
        for (FriendDto friend : friends) {
            if (friend.getFriendId().equals(toUserId)) {
                return true;
            }
        }
        return false;
    }

    @Transactional
    public boolean transfer(Long fromUserId, Long toUserId, BigDecimal amount) {
        if (!isFriend(fromUserId, toUserId)) {
            return false;
        }
        synchronized (this){

            Account fromAccount = accountRepository.findById(fromUserId).orElse(null);
            Account toAccount = accountRepository.findById(toUserId).orElse(null);
            if (fromAccount == null || toAccount == null) {
                return false;
            }
            if (fromAccount.getBalance().compareTo(amount) < 0) {
                return false;
            }
            fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
            toAccount.setBalance(toAccount.getBalance().add(amount));
            accountRepository.save(fromAccount);
            accountRepository.save(toAccount);
        }
        return true;
    }
}
