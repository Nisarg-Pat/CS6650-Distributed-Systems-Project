package com.example.cs6650.server.controller.command;

import com.example.cs6650.server.model.User;
import com.example.cs6650.server.service.IService;
import com.example.cs6650.server.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public class SignupCommand implements Command{
    User user;


    public SignupCommand(User user) {
        this.user = user;
    }

    @Override
    public ResponseEntity<Object> execute(IService service) {
        UserService userService = (UserService) service;
        System.out.println("In Create User:"+user);
        if(userService.getUserFromUsername(user.getUsername()).isPresent()) {
            return new ResponseEntity<>("Username Already present", HttpStatus.CONFLICT);
        }
        userService.createUser(user);
        return new ResponseEntity<>(userService.getUserFromUsername(user.getUsername()).get(), HttpStatus.CREATED);
    }
}
