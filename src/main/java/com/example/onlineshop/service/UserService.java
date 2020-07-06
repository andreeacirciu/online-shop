package com.example.onlineshop.service;

import com.example.onlineshop.domain.User;
import com.example.onlineshop.exception.ResourceNotFoundException;
import com.example.onlineshop.persistence.UserRepository;
import com.example.onlineshop.transfer.user.CreateUserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserService(UserRepository userRepository, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    public User createUser(CreateUserRequest request) {

        LOGGER.info("Creating user {}", request);

        User user = objectMapper.convertValue(request, User.class);

        return userRepository.save(user);
    }

    public User getUser(long id) {
        LOGGER.info("Retrieving user {}", id);
        return userRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("User " + id + " not found."));
    }
}
