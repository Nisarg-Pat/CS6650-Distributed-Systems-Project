package com.example.cs6650.server.service;

import com.example.cs6650.server.model.Book;
import com.example.cs6650.server.model.User;
import com.example.cs6650.server.repository.BookRepository;
import com.example.cs6650.server.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CartRepository cartRepository;

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public Optional<Book> getBook(int id) {
        return bookRepository.findById(id);
    }

    public List<Book> getListOfBooks(User user) {
        return bookRepository.findBookByUserId(user.getId());
    }

    public void sellBook(Book oldBook) {
        if(bookRepository.findById(oldBook.getId()).isPresent()) {
            Book book = bookRepository.findById(oldBook.getId()).get();
            book.setStatus("sell");
            book.setSellPrice(oldBook.getSellPrice());
            bookRepository.save(book);
        }
    }

    public void shelfBook(Book oldBook) {
        if(bookRepository.findById(oldBook.getId()).isPresent()) {
            Book book = bookRepository.findById(oldBook.getId()).get();
            book.setStatus("shelf");
            book.setSellPrice(0);
            bookRepository.save(book);
        }
    }

    public void deleteBook(Book book) {
        if(bookRepository.findById(book.getId()).isPresent()) {
            bookRepository.deleteById(book.getId());
        }
    }

    public List<Book> searchBook(String name) {
        return bookRepository.findAll().stream().filter((book) -> book.getName().startsWith(name) && book.getStatus().equals("sell")).toList();
    }


    public List<Book> buyBooks(User newUser, List<Book> bookList) {
        List<Book> buyBooks = new ArrayList<>();
        for(Book book: bookList) {
            Book newBook = new Book();
            newBook.copyFrom(book);
            book.setStatus("sold");
            newBook.setUserId(newUser.getId());
            newBook.setStatus("bought");
            newBook.setSellPrice(0);
            cartRepository.delete(cartRepository.getCartByUserIdAndBookId(newUser.getId(), book.getId()).get());
            bookRepository.save(book);
            bookRepository.save(newBook);
            buyBooks.add(newBook);
        }
        return buyBooks;
    }

    public List<Book> allBooks() {
        return bookRepository.findAll();
    }
}
