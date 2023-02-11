package com.example.bankingproject.controller;


import com.example.bankingproject.domain.User;
import com.example.bankingproject.dto.user.request.UserCreateRequest;
import com.example.bankingproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;



    @GetMapping("/")
    public String main () {
        return "index";
    }

    @GetMapping("/new")
    public String saveUser(Model model) {
        model.addAttribute("userCreateRequest", new UserCreateRequest());
        return "user/signup";
    }

    @PostMapping("new")
    public String saveUser(@Valid UserCreateRequest userCreateRequest,
                           BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "user/signup";
        }

        try {
            User user = User.createUser(userCreateRequest, passwordEncoder);
            userService.saveUser(user);
        }catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "user/signup";
        }

        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginUser() {
        return "user/login";
    }

    @GetMapping("/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호가 틀렸습니다.");
        return "user/login";
    }

    @GetMapping("/user/friends")
    public void saveFriends() {

    }


}
