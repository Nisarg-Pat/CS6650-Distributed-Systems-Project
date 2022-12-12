package com.example.cs6650.server.controller.command;

import com.example.cs6650.server.model.Book;
import com.example.cs6650.server.model.Cart;
import com.example.cs6650.server.model.User;
import com.example.cs6650.server.service.BookService;
import com.example.cs6650.server.service.CartService;
import com.example.cs6650.server.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddToCartCommand extends Command{

    public AddToCartCommand(Cart cart) {
        super();
        this.type = "addToCart";
        this.cart = cart;
    }

    public ResponseEntity<Object> execute(UserService userService, BookService bookService, CartService cartService) {
        System.out.println("Add:"+cart);
        Optional<User> user = userService.getUserFromId(cart.getUserId());
        if(user.isEmpty()) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
        Optional<Book> book = bookService.getBook(cart.getBookId());
        if(book.isEmpty()) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
        cartService.addCart(cart);
        return new ResponseEntity<>(book.get(), HttpStatus.CREATED);
    }
}
