package com.example.cs6650.server.coordinator;

import com.example.cs6650.server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@CrossOrigin(origins = {"*"})
public class ServerController {

    @Autowired
    CoordinatorService coordinatorService;

    @PostMapping("/addserver")
    public ResponseEntity<Server> addServer(@RequestBody Server server) {
        System.out.println("Server:"+server);
        Server servero = coordinatorService.addServer(server);
        return new ResponseEntity<>(servero, HttpStatus.CREATED);
    }
}
