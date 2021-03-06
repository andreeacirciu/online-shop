package com.example.onlineshop.web;


import com.example.onlineshop.domain.User;
import com.example.onlineshop.service.UserService;
import com.example.onlineshop.transfer.user.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

@Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(CreateUserRequest request){

    User user = userService.createUser(request);

    return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id){
        User user = userService.getUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
