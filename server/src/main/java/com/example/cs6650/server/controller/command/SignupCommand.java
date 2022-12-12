package com.example.cs6650.server.controller.command;

import com.example.cs6650.server.model.User;
import com.example.cs6650.server.service.IService;
import com.example.cs6650.server.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.Serializable;

public class SignupCommand extends Command {

    public SignupCommand(User user) {
        super();
        this.type = "signup";
        this.user = user;
    }

    public SignupCommand() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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
