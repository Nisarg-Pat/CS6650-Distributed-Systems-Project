package com.example.cs6650.server.controller.command;

import com.example.cs6650.server.model.Book;
import com.example.cs6650.server.model.Cart;
import com.example.cs6650.server.model.User;
import com.example.cs6650.server.service.BookService;
import com.example.cs6650.server.service.CartService;
import com.example.cs6650.server.service.IService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class GetCartOfUserCommand extends Command{

    public GetCartOfUserCommand(User user) {
        super();
        this.type = "getCart";
        this.user = user;
    }

    public ResponseEntity<Object> execute(BookService bookService, CartService cartService) {
        List<Cart> cartList = cartService.getCartOfUser(user);
        List<Book> bookList = new ArrayList<>();
        for(Cart cart: cartList) {
            if(bookService.getBook(cart.getBookId()).isPresent()) {
                bookList.add(bookService.getBook(cart.getBookId()).get());
            }
        }
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }
}
