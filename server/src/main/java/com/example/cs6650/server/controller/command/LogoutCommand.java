package com.example.cs6650.server.controller.command;

import com.example.cs6650.server.model.User;
import com.example.cs6650.server.service.IService;
import com.example.cs6650.server.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class LogoutCommand extends Command{

    public LogoutCommand(User user) {
        super();
        this.type = "logout";
        this.user = user;
    }

    public ResponseEntity<Object> execute(IService service) {
        UserService userService = (UserService) service;
        Optional<User> getUser = userService.getUserFromUsernamePassword(user.getUsername(), user.getPassword());
        if(getUser.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(getUser.get(), HttpStatus.OK);
    }
}
