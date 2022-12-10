package com.example.cs6650.server.service;

import com.example.cs6650.server.model.Book;
import com.example.cs6650.server.model.User;
import com.example.cs6650.server.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public Optional<Book> getBook(int id) {
        return bookRepository.findById(id);
    }

    public List<Book> getListOfBooks(User user) {
        return bookRepository.findBookByUserId(user.getId());
    }
}