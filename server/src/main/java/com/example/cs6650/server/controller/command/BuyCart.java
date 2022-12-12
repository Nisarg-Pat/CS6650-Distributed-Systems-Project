package com.example.cs6650.server.controller.command;

import com.example.cs6650.server.model.Book;
import com.example.cs6650.server.model.Cart;
import com.example.cs6650.server.model.CartBody;
import com.example.cs6650.server.model.User;
import com.example.cs6650.server.service.BookService;
import com.example.cs6650.server.service.CartService;
import com.example.cs6650.server.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BuyCart extends Command{

    public BuyCart(CartBody cartbody) {
        super();
        this.type = "buyCart";
        this.cartBody = cartbody;
    }

    public ResponseEntity<Object> execute(UserService userService, BookService bookService, CartService cartService) {
        System.out.println(cartBody);
        Optional<User> user = userService.getUserFromId(cartBody.getNewUser().getId());
        if(user.isEmpty()) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
        List<Book> bookList = new ArrayList<>();
        for(Book cartbook: cartBody.getBookList()) {
            Optional<Book> book = bookService.getBook(cartbook.getId());
            if(book.isEmpty()) {
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            } else if (!book.get().getStatus().equals("sell")) {
                return new ResponseEntity<>(false, HttpStatus.CONFLICT);
            }
            bookList.add(book.get());
        }
        List<Book> buyBooks = bookService.buyBooks(user.get(), bookList);
        return new ResponseEntity<>(buyBooks, HttpStatus.OK);
    }
}
