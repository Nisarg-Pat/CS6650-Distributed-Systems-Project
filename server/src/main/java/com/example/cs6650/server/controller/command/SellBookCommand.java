package com.example.cs6650.server.controller.command;

import com.example.cs6650.server.model.Book;
import com.example.cs6650.server.service.BookService;
import com.example.cs6650.server.service.IService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class SellBookCommand extends Command{

    public SellBookCommand(Book book) {
        super();
        this.type = "sellBook";
        this.book = book;
    }

    public ResponseEntity<Object> execute(IService service) {
        BookService bookService = (BookService) service;
        bookService.sellBook(book);
        return new ResponseEntity<>(bookService.getBook(book.getId()).get(), HttpStatus.OK);
    }
}
