package com.example.cs6650.server.controller;

import com.example.cs6650.server.model.Book;
import com.example.cs6650.server.model.Cart;
import com.example.cs6650.server.model.CartBody;
import com.example.cs6650.server.model.User;
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

    @PostMapping("/usercart")
    public ResponseEntity<List<Book>> getCartOfUser(@RequestBody User user) {
        List<Cart> cartList = cartService.getCartOfUser(user);
        List<Book> bookList = new ArrayList<>();
        for(Cart cart: cartList) {
            if(bookService.getBook(cart.getBookId()).isPresent()) {
                bookList.add(bookService.getBook(cart.getBookId()).get());
            }
        }
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @PostMapping("/addtocart")
    public ResponseEntity<Book> addToCart(@RequestBody Cart cart) {
        Optional<User> user = userService.getUserFromId(cart.getUserId());
        if(user.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Optional<Book> book = bookService.getBook(cart.getBookId());
        if(book.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        cartService.addCart(cart);
        return new ResponseEntity<>(book.get(), HttpStatus.CREATED);
    }

    @PostMapping("/buycart")
    public ResponseEntity<List<Book>> buyCart(@RequestBody CartBody body) {
        System.out.println(body);
        Optional<User> user = userService.getUserFromId(body.getNewUser().getId());
        if(user.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        List<Book> bookList = new ArrayList<>();
        for(Book cartbook: body.getBookList()) {
            Optional<Book> book = bookService.getBook(cartbook.getId());
            if(book.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            bookList.add(book.get());
        }
        List<Book> buyBooks = bookService.buyBooks(user.get(), bookList);
        return new ResponseEntity<>(buyBooks, HttpStatus.OK);
    }
}
