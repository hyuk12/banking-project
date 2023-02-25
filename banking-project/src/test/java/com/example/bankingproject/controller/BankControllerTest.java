package com.example.bankingproject.controller;

import com.example.bankingproject.controller.dto.FriendDto;
import com.example.bankingproject.domain.Account;
import com.example.bankingproject.domain.User;
import com.example.bankingproject.service.AlertService;
import com.example.bankingproject.service.BankService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class BankControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BankService bankService;

    @MockBean
    private AlertService alertService;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void register() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername("test");
        user.setPassword("1234");

        given(bankService.register(any(User.class))).willReturn(user);

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isOk());

        verify(bankService, times(1)).register(any(User.class));
    }

    @Test
    public void login() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername("test");
        user.setPassword("1234");

        given(bankService.login(any(User.class))).willReturn(user);

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isOk());

        verify(bankService, times(1)).login(any(User.class));
    }

    @Test
    public void getFriends() throws Exception {
        List<FriendDto> friendList = List.of(
                new FriendDto(2L, "friend1"),
                new FriendDto(3L, "friend2")
        );

        given(bankService.getFriends(anyLong())).willReturn(friendList);

        mockMvc.perform(get("/friends?userId=1"))
                .andExpect(status().isOk());

        verify(bankService, times(1)).getFriends(anyLong());
    }

    @Test
    public void addFriend() throws Exception {
        FriendDto friend = new FriendDto(2L, "friend1");

        given(bankService.addFriend(anyLong(), any(FriendDto.class))).willReturn(friend);

        mockMvc.perform(post("/friends?userId=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(friend)))
                .andExpect(status().isOk());

        verify(bankService, times(1)).addFriend(anyLong(), any(FriendDto.class));
    }

    @Test
    public void getAccount() throws Exception {
        Account account = new Account();
        account.setUserId(1L);

        given(bankService.getAccount(anyLong())).willReturn(account);

        mockMvc.perform(get("/account?userId=1"))
                .andExpect(status().isOk());

        verify(bankService, times(1)).getAccount(anyLong());
    }
}
