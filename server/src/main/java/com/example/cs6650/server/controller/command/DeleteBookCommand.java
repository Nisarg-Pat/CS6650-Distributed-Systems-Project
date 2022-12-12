package com.example.cs6650.server.controller.command;

import com.example.cs6650.server.model.Book;
import com.example.cs6650.server.service.BookService;
import com.example.cs6650.server.service.IService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class DeleteBookCommand extends Command{

    public DeleteBookCommand(Book book) {
        super();
        this.type = "deleteBook";
        this.book = book;
    }

    public ResponseEntity<Object> execute(IService service) {
        BookService bookService = (BookService) service;
        int id = book.getId();
        bookService.deleteBook(book);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
