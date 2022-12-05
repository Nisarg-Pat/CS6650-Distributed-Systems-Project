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
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
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
//
//    @GetMapping("/signup")
//    public ResponseEntity<User> listUsers(@RequestBody User user) {
//        Optional<User> getUser = userService.getUserFromUsernamePassword(user.getUsername(), user.getPassword());
//        if(getUser.isEmpty()) {
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<>(getUser.get(), HttpStatus.OK);
//    }

    @PostMapping("/signup")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        System.out.println("In Create User:"+user);
        if(userService.getUserFromUsername(user.getUsername()).isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        userService.createUser(user);

        return new ResponseEntity<>(userService.getUserFromUsername(user.getUsername()).get(), HttpStatus.CREATED);
    }
}