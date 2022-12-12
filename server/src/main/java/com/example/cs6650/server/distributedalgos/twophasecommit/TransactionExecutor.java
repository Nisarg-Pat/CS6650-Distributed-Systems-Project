package com.example.cs6650.server.distributedalgos.twophasecommit;

import com.example.cs6650.server.controller.command.*;
import com.example.cs6650.server.service.BookService;
import com.example.cs6650.server.service.CartService;
import com.example.cs6650.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

public class TransactionExecutor {

    public static ResponseEntity<Object> execute(Transaction transaction, UserService userService, BookService bookService, CartService cartService) {
        if(transaction.getCommand().getType().equals("signup")) {
            return new SignupCommand(transaction.getCommand().getUser()).execute(userService);
        }
        if(transaction.getCommand().getType().equals("logout")) {
            return new LogoutCommand(transaction.getCommand().getUser()).execute(userService);
        }
        if(transaction.getCommand().getType().equals("login")) {
            return new LoginCommand(transaction.getCommand().getUser()).execute(userService);
        }
        if(transaction.getCommand().getType().equals("addBook")) {
            return new AddBookCommand(transaction.getCommand().getBook()).execute(bookService);
        }
        if(transaction.getCommand().getType().equals("sellBook")) {
            return new SellBookCommand(transaction.getCommand().getBook()).execute(bookService);
        }
        if(transaction.getCommand().getType().equals("deleteBook")) {
            return new DeleteBookCommand(transaction.getCommand().getBook()).execute(bookService);
        }
        if(transaction.getCommand().getType().equals("shelfBook")) {
            return new ShelfBookCommand(transaction.getCommand().getBook()).execute(bookService);
        }
        if(transaction.getCommand().getType().equals("addToCart")) {
            return new AddToCartCommand(transaction.getCommand().getCart()).execute(userService, bookService, cartService);
        }
        if(transaction.getCommand().getType().equals("buyCart")) {
            return new BuyCart(transaction.getCommand().getCartBody()).execute(userService, bookService, cartService);
        }
        if(transaction.getCommand().getType().equals("deleteFromCart")) {
            return new DeleteFromCart(transaction.getCommand().getCart()).execute(userService, bookService, cartService);
        }
        if(transaction.getCommand().getType().equals("getCart")) {
            return new GetCartOfUserCommand(transaction.getCommand().getUser()).execute(bookService, cartService);
        }
        return null;
    }
}
