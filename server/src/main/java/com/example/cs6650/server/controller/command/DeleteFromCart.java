package com.example.cs6650.server.controller.command;

import com.example.cs6650.server.common.Log;
import com.example.cs6650.server.model.Book;
import com.example.cs6650.server.model.Cart;
import com.example.cs6650.server.model.User;
import com.example.cs6650.server.service.BookService;
import com.example.cs6650.server.service.CartService;
import com.example.cs6650.server.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class DeleteFromCart extends Command{

    public DeleteFromCart(Cart cart) {
        super();
        this.type = "deleteFromCart";
        this.cart = cart;
    }

    public ResponseEntity<Object> execute(UserService userService, BookService bookService, CartService cartService) {
        Log.logln("Remove"+cart);
        Optional<Cart> ocart = cartService.getCart(cart);
        if(ocart.isEmpty()) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
        cartService.removeCart(ocart.get());
        return new ResponseEntity<>(cart.getBookId(), HttpStatus.OK);
    }
}
