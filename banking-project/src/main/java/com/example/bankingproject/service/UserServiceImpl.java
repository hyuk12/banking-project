package com.example.bankingproject.service;

import com.example.bankingproject.config.JwtTokenProvider;
import com.example.bankingproject.config.SecurityUtil;
import com.example.bankingproject.domain.User;
import com.example.bankingproject.dto.jwt.TokenInfo;
import com.example.bankingproject.dto.user.request.UserCreateRequest;
import com.example.bankingproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;






    @Transactional
    public TokenInfo login(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        String getUser = SecurityUtil.getCurrentMemberId();
        System.out.println(getUser);
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);
        return tokenInfo;
    }


}
