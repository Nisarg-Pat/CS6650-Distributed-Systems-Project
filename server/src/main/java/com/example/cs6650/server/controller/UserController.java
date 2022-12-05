package com.example.cs6650.server.controller;


import com.example.cs6650.server.common.ApiResponse;
import com.example.cs6650.server.model.User;
import com.example.cs6650.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> listUsers() {
        List<User> users = userService.listUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createUser(@RequestBody User user) {
        if(userService.getUserFromUsername(user.getUsername()).isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "User already exists"), HttpStatus.CONFLICT);
        }
        userService.createUser(user);
        return new ResponseEntity<>(new ApiResponse(true, "User created successfully"), HttpStatus.CREATED);
    }
}
