package com.example.cs6650.server.controller.command;

import com.example.cs6650.server.model.User;
import com.example.cs6650.server.service.IService;
import com.example.cs6650.server.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class LoginCommand implements Command{

    User user;

    public LoginCommand(User user) {
        this.user = user;
    }

    @Override
    public ResponseEntity<Object> execute(IService service) {
        System.out.println("In Login User:"+user);
        UserService userService = (UserService) service;
        Optional<User> getUser = userService.getUserFromUsernamePassword(user.getUsername(), user.getPassword());
        if(getUser.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(getUser.get(), HttpStatus.OK);
    }
}
