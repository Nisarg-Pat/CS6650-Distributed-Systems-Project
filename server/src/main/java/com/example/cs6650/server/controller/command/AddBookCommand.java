package com.example.cs6650.server.controller.command;

import com.example.cs6650.server.model.Book;
import com.example.cs6650.server.model.User;
import com.example.cs6650.server.service.BookService;
import com.example.cs6650.server.service.IService;
import com.example.cs6650.server.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class AddBookCommand extends Command{

    public AddBookCommand(Book book) {
        super();
        this.type = "addBook";
        this.book = book;
    }

    public ResponseEntity<Object> execute(IService service) {
        BookService bookService = (BookService) service;
        bookService.addBook(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }
}
