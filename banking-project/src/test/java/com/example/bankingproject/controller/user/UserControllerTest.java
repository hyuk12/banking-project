package com.example.bankingproject.controller.user;

import com.example.bankingproject.domain.User;

import com.example.bankingproject.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("회원가입 - 성공")
    void addUserSuccess() throws Exception {
        String username = "user1";
        String password = "pass1";

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("{\"username\": \"%s\", \"password\": \"%s\"}", username, password)))
                .andExpect(status().isOk());

        User user = userRepository.findByUsername(username).orElse(null);
        assertNotNull(user);
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
    }

    @Test
    @DisplayName("회원가입 - 실패 (중복된 username)")
    void addUserFailureDuplicateUsername() throws Exception {
        String username = "user1";
        String password1 = "pass1";
        String password2 = "pass2";

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("{\"username\": \"%s\", \"password\": \"%s\"}", username, password1)))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("{\"username\": \"%s\", \"password\": \"%s\"}", username, password2)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("로그인 - 성공")
    void loginSuccess() throws Exception {
        String username = "user1";
        String password = "pass1";

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("{\"username\": \"%s\", \"password\": \"%s\"}", username, password)))
                .andExpect(status().isOk());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("{\"username\": \"%s\", \"password\": \"%s\"}", username, password)))
                .andExpect(status().isOk())
                .andReturn();

        String token = result.getResponse().getContentAsString();
        assertNotNull(token);
    }

    @Test
    @DisplayName("로그인 - 실패 (잘못된 username)")
    void loginFailureWrongUsername() throws Exception {
        String username = "user1";
        String password = "pass1";

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("{\"username\": \"%s\", \"password\": \"%s\"}", username, password)))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"wrongusername\", \"password\": \"pass1\"}"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("로그인 - 실패 (잘못된 password)")
    void loginFailureWrongPassword() throws Exception {
        String username = "user1";
        String password = "pass1";

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("{\"username\": \"%s\", \"password\": \"%s\"}", username, password)))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"user1\", \"password\": \"wrongpassword\"}"))
                .andExpect(status().isUnauthorized());
    }
}
