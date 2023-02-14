package com.example.bankingproject.controller;

import com.example.bankingproject.controller.dto.UserDto;
import com.example.bankingproject.domain.User;
import com.example.bankingproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController("/users")
public class UserController {
    private final UserService userService;


    @GetMapping("/users")
    public String addUser() {
        return "users";
    }


    @PostMapping("users")
    public ResponseEntity<User> addUser(@RequestBody UserDto userDto) {
        User user = userService.addUser(userDto.getUsername(), userDto.getPassword());
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody UserDto userDto) {
        User user = userService.getUserByUsername(userDto.getUsername());
        if (user != null && user.getPassword().equals(userDto.getPassword())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

//    @GetMapping("/users")
//    public ResponseEntity<User> getUserByUsername(@RequestParam String username) {
//        User user = userService.getUserByUsername(username);
//        return ResponseEntity.ok(user);
//    }
}
