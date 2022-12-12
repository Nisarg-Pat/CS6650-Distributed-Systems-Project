package com.example.cs6650.server.controller.command;

import com.example.cs6650.server.model.Book;
import com.example.cs6650.server.service.BookService;
import com.example.cs6650.server.service.IService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ShelfBookCommand extends Command{

    public ShelfBookCommand(Book book) {
        super();
        this.type = "shelfBook";
        this.book = book;
    }

    public ResponseEntity<Object> execute(IService service) {
        BookService bookService = (BookService) service;
        bookService.shelfBook(book);
        return new ResponseEntity<>(bookService.getBook(book.getId()).get(), HttpStatus.OK);
    }
}
