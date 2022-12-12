package com.example.cs6650.server.coordinator;

import com.example.cs6650.server.common.Log;
import com.example.cs6650.server.model.Book;
import com.example.cs6650.server.model.User;
import com.example.cs6650.server.repository.BookRepository;
import com.example.cs6650.server.repository.CartRepository;
import com.example.cs6650.server.repository.UserRepository;
import com.example.cs6650.server.service.BookService;
import com.example.cs6650.server.service.CartService;
import com.example.cs6650.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@CrossOrigin(origins = {"*"})
public class ServerController {

    @Autowired
    CoordinatorService coordinatorService;

    @Autowired
    BookService bookService;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartService cartService;

    @Autowired
    RestService restService;

    @Autowired
    CartRepository cartRepository;


    @PostMapping("/addserver")
    public ResponseEntity<Object> addServer(@RequestBody Server server) {
        Server servero = coordinatorService.addServer(server);
        Log.logln("Added Server:"+server);
        ServerData serverData = new ServerData(userService.allUsers(), cartService.allCarts(), bookService.allBooks());
        Object response = restService.post(restService.generateURL(server.getHost(), server.getPort(), "serverdata"), serverData);
        return new ResponseEntity<>(servero, HttpStatus.CREATED);
    }

    @PostMapping("/allservers")
    public ResponseEntity<List<Server>> allServer() {
        return new ResponseEntity<>(coordinatorService.serverList(), HttpStatus.OK);
    }

    @PostMapping("/serverdata")
    public ResponseEntity<Object> makeData(@RequestBody ServerData serverData) {
//        Log.logln("Coordinator Data: "+serverData);
        Log.logln("Copying state from Coordinator");
        userRepository.saveAll(serverData.getUsers());
        bookRepository.saveAll(serverData.getBooks());
        cartRepository.saveAll(serverData.getCarts());
        Log.logln("State Copied");
        return new ResponseEntity<>(false, HttpStatus.OK);
    }
}
