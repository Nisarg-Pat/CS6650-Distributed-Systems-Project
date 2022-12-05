package com.example.cs6650.server.service;

import com.example.cs6650.server.model.User;
import com.example.cs6650.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public Optional<User> getUserFromId(Integer id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserFromUsernamePassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public Optional<User> getUserFromUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void updateUser(Integer userID, User newUser) {
        if(userRepository.findById(userID).isPresent()) {
            User user = userRepository.findById(userID).get();
            user.setUsername(newUser.getUsername());
            user.setPassword(newUser.getPassword());
            user.setName(newUser.getName());
            user.setEmail(newUser.getEmail());
            userRepository.save(user);
        }

    }

}
