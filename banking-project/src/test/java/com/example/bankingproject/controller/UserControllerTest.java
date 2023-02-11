package com.example.bankingproject.controller;

import com.example.bankingproject.dto.user.request.UserCreateRequest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class UserControllerTest {

    private static UserController controller;
    private static UserCreateRequest request;

    @Test
    public void testSaveUser(UserCreateRequest request) {
       String username = "hyuko12";
       String password = "12345";
       request.setUsername(username);
       request.setPassword(password);

       controller.saveUser(request);
    }

}
