package com.example.onlineshop;


import com.example.onlineshop.domain.User;
import com.example.onlineshop.domain.UserRole;
import com.example.onlineshop.service.UserService;
import com.example.onlineshop.steps.UserTestSteps;
import com.example.onlineshop.transfer.user.CreateUserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@SpringBootTest
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserTestSteps userTestSteps;


    @Test
    public void createUser_whenValidRequest_thenReturnCreatedUser(){

        userTestSteps.createUser();

    }
    @Test
    public void getUser_whenExistingUser_thenReturnUser(){
        User user = userTestSteps.createUser();

        User userResponse = userService.getUser(user.getId());

        assertThat(userResponse, notNullValue());
        assertThat(userResponse.getId(), is(user.getId()));
        assertThat(userResponse.getRole(), is(user.getRole()));
        assertThat(userResponse.getFirstName(), is(user.getFirstName()));
        assertThat(userResponse.getLastName(), is(user.getLastName()));
    }





}
