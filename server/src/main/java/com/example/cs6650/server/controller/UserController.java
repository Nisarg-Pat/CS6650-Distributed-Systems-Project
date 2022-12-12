package com.example.cs6650.server.controller;


import com.example.cs6650.server.common.ApiResponse;
import com.example.cs6650.server.controller.command.Command;
import com.example.cs6650.server.controller.command.LoginCommand;
import com.example.cs6650.server.controller.command.LogoutCommand;
import com.example.cs6650.server.controller.command.SignupCommand;
import com.example.cs6650.server.distributedalgos.twophasecommit.Transaction;
import com.example.cs6650.server.distributedalgos.twophasecommit.TwoPhaseCommitController;
import com.example.cs6650.server.model.User;
import com.example.cs6650.server.repository.MyServerRepository;
import com.example.cs6650.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/user")
public class UserController extends TwoPhaseCommitController {

    @Autowired
    private UserService userService;

    @Autowired
    private MyServerRepository myServer;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> listUsers() {
        List<User> users = userService.listUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        Command command = new SignupCommand(user);
        Transaction transaction = new Transaction(Long.parseLong(""+myServer.getMyServerById(1).getPort()+""+System.currentTimeMillis()), command, "userService");
        return performTransaction(transaction);
    }


    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody User user) {
        Command command = new LoginCommand(user);
        Transaction transaction = new Transaction(Long.parseLong(""+myServer.getMyServerById(1).getPort()+""+System.currentTimeMillis()), command, "userService");
        return performTransaction(transaction);
    }

    @PostMapping("/logout")
    public ResponseEntity<Object> logout(@RequestBody User user) {
        Command command = new LogoutCommand(user);
        Transaction transaction = new Transaction(Long.parseLong(""+myServer.getMyServerById(1).getPort()+""+System.currentTimeMillis()), command, "userService");
        return performTransaction(transaction);
    }
}
