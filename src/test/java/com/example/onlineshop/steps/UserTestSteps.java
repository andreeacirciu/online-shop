package com.example.onlineshop.steps;

import com.example.onlineshop.domain.User;
import com.example.onlineshop.domain.UserRole;
import com.example.onlineshop.service.UserService;
import com.example.onlineshop.transfer.user.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@Component
public class UserTestSteps {


    @Autowired
    private UserService userService;

    @Autowired
    private UserTestSteps userTestSteps;


    public User createUser() {
        CreateUserRequest request = new CreateUserRequest();

        request.setRole(UserRole.CUSTOMER);
        request.setFirstName("Test First Name");
        request.setLastName("Test Last Name");

        User user = userService.createUser(request);

        assertThat(user, notNullValue());
        assertThat(user.getId(), greaterThan(0L));
        assertThat(user.getRole(), is(request.getRole().name()));
        assertThat(user.getFirstName(), is(request.getFirstName()));
        assertThat(user.getLastName(), is(request.getLastName()));

        return user;
    }
}
