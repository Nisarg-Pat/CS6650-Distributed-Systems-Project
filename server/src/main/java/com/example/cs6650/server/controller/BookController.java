package com.example.cs6650.server.controller;

import com.example.cs6650.server.model.Book;
import com.example.cs6650.server.model.User;
import com.example.cs6650.server.service.BookService;
import com.example.cs6650.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/book")
public class BookController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @PostMapping("/addbook")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        System.out.println("In Add Book:"+book);
        Optional<User> user = userService.getUserFromId(book.getUserId());
        if(user.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        bookService.addBook(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @PostMapping("/booklist")
    public ResponseEntity<List<Book>> getBookListOfUser(@RequestBody User user) {
        return new ResponseEntity<>(bookService.getListOfBooks(user), HttpStatus.OK);
    }

    @PostMapping("/sell")
    public ResponseEntity<Book> sellBook(@RequestBody Book book) {
        System.out.println("In Sell Book:"+book);
        Optional<User> user = userService.getUserFromId(book.getUserId());
        if(user.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        bookService.sellBook(book);
        return new ResponseEntity<>(bookService.getBook(book.getId()).get(), HttpStatus.OK);
    }
}
