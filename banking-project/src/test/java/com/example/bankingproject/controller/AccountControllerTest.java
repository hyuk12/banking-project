package com.example.bankingproject.controller;

import com.example.bankingproject.controller.dto.AccountDto;
import com.example.bankingproject.domain.Account;
import com.example.bankingproject.domain.User;
import com.example.bankingproject.repository.AccountRepository;
import com.example.bankingproject.repository.UserRepository;
import com.example.bankingproject.service.AccountCreationException;
import com.example.bankingproject.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    private User user;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        accountRepository.deleteAll();

        user = userRepository.save(new User("user1", "pass1"));
    }

    @Test
    @DisplayName("계좌 생성 - 성공")
    @WithMockUser(username = "user1", password = "pass1")
    void addAccountSuccess() throws Exception {
        // 유저가 생성한 계좌가 없는 경우 계좌 생성
        if (!accountRepository.existsByUser(userRepository.findByUsername("user1").orElse(null))) {
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/accounts")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{}"))
                    .andExpect(status().isOk())
                    .andReturn();

            String responseString = result.getResponse().getContentAsString();
            assertNotNull(responseString);

            AccountDto accountDto = AccountDto.fromEntity((Account) Objects.requireNonNull(accountRepository.findByUser(userRepository.findByUsername("user1").orElse(null)).orElse(null)));
            assertNotNull(accountDto);
            assertEquals(responseString, accountDto.toString());
        }
    }

    @Test
    @DisplayName("계좌 생성 - 실패 (이미 생성한 계좌가 있음)")
    @WithMockUser(username = "user1", password = "pass1")
    void addAccountFailureAlreadyExists() throws Exception, AccountCreationException {
        // 계좌 생성
        accountService.createAccount(userRepository.findByUsername("user1").orElse(null).getId());

        // 이미 계좌가 있는 경우
        mockMvc.perform(MockMvcRequestBuilders.post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("계좌 생성 - 실패 (알파벳 포함)")
    @WithMockUser(username = "user1", password = "pass1")
    void addAccountFailureHasAlphabet() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"accountNumber\": \"123abc4567890\"}"))
                .andExpect(status().isBadRequest());
    }
}
