package com.example.bankingproject.controller.friend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FriendControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getFriends() throws Exception {
        mockMvc.perform(get("/friends/1"))
                .andExpect(status().isOk());
    }

    @Test
    void isFriend() throws Exception {
        mockMvc.perform(get("/friends/1/is-friend/2"))
                .andExpect(status().isOk());
    }

    @Test
    void addFriend() throws Exception {
        mockMvc.perform(post("/friends/1/add-friend/2"))
                .andExpect(status().isOk());
    }

    @Test
    void removeFriend() throws Exception {
        mockMvc.perform(delete("/friends/1/remove-friend/2"))
                .andExpect(status().isNoContent());
    }
}
