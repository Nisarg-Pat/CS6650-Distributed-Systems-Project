package com.example.cs6650.server.coordinator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin(origins = {"http://localhost:3000"})
public class CoordinatorController {

    @Autowired
    CoordinatorService coordinatorService;

    
}
