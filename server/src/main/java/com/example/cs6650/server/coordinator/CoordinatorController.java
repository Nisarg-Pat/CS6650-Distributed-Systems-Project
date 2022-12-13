package com.example.cs6650.server.coordinator;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class CoordinatorController {

    @Autowired
    CoordinatorService coordinatorService;

    @PostMapping("/getServer")
    public ResponseEntity<Object> getServer() {
        Server server = coordinatorService.getRandomServer();
        System.out.println("Assigning:"+server);
        return new ResponseEntity<>(server, HttpStatus.OK);
    }

}
