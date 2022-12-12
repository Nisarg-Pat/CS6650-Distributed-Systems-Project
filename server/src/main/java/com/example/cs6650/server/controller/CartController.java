package com.example.cs6650.server.controller;

import com.example.cs6650.server.controller.command.*;
import com.example.cs6650.server.coordinator.RestService;
import com.example.cs6650.server.distributedalgos.twophasecommit.Transaction;
import com.example.cs6650.server.model.Book;
import com.example.cs6650.server.model.Cart;
import com.example.cs6650.server.model.CartBody;
import com.example.cs6650.server.model.User;
import com.example.cs6650.server.repository.MyServerRepository;
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
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @Autowired
    RestService restService;

    @Autowired
    private MyServerRepository myServer;

    @PostMapping("/usercart")
    public ResponseEntity<Object> getCartOfUser(@RequestBody User user) {
        Command command = new GetCartOfUserCommand(user);
        Transaction transaction = new Transaction(Long.parseLong(System.currentTimeMillis()+""+myServer.getMyServerById(1).getPort()), command);
        return restService.post(restService.generateURL("localhost", 8080, "propose"), transaction);
    }

    @PostMapping("/addtocart")
    public ResponseEntity<Object> addToCart(@RequestBody Cart cart) {
        Command command = new AddToCartCommand(cart);
        Transaction transaction = new Transaction(Long.parseLong(System.currentTimeMillis()+""+myServer.getMyServerById(1).getPort()), command);
        return restService.post(restService.generateURL("localhost", 8080, "propose"), transaction);
    }

    @PostMapping("/remove")
    public ResponseEntity<Object> deleteFromCart(@RequestBody Cart cart) {
        Command command = new DeleteFromCart(cart);
        Transaction transaction = new Transaction(Long.parseLong(System.currentTimeMillis()+""+myServer.getMyServerById(1).getPort()), command);
        return restService.post(restService.generateURL("localhost", 8080, "propose"), transaction);

    }

    @PostMapping("/buycart")
    public ResponseEntity<Object> buyCart(@RequestBody CartBody body) {
        Command command = new BuyCart(body);
        Transaction transaction = new Transaction(Long.parseLong(System.currentTimeMillis()+""+myServer.getMyServerById(1).getPort()), command);
        return restService.post(restService.generateURL("localhost", 8080, "propose"), transaction);

    }
}
