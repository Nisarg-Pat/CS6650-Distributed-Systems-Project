package com.example.cs6650.server.controller;

import com.example.cs6650.server.controller.command.AddBookCommand;
import com.example.cs6650.server.controller.command.Command;
import com.example.cs6650.server.controller.command.SellBookCommand;
import com.example.cs6650.server.controller.command.SignupCommand;
import com.example.cs6650.server.coordinator.RestService;
import com.example.cs6650.server.distributedalgos.paxos.PaxosController;
import com.example.cs6650.server.distributedalgos.ricartoagarwala.RicartAgarwala;
import com.example.cs6650.server.distributedalgos.twophasecommit.Transaction;
import com.example.cs6650.server.model.Book;
import com.example.cs6650.server.model.User;
import com.example.cs6650.server.repository.MyServerRepository;
import com.example.cs6650.server.service.BookService;
import com.example.cs6650.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/book")
public class BookController extends RicartAgarwala {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private MyServerRepository myServer;

    @Autowired
    private RestService restService;

    @PostMapping("/addbook")
    public ResponseEntity<Object> addBook(@RequestBody Book book) {
        System.out.println("In Add Book:"+book);
        Optional<User> user = userService.getUserFromId(book.getUserId());
        if(user.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Command command = new AddBookCommand(book);
        Transaction transaction = new Transaction(Long.parseLong(System.currentTimeMillis()+""+myServer.getMyServerById(1).getPort()), command, "bookService");
        return restService.post(restService.generateURL("localhost", 8080, "propose"), transaction);
    }

    @PostMapping("/booklist")
    public ResponseEntity<List<Book>> getBookListOfUser(@RequestBody User user) {
        enterSection();
        List<Book> bookList = bookService.getListOfBooks(user);
        exitSection();
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @PostMapping("/sell")
    public ResponseEntity<Object> sellBook(@RequestBody Book book) {
        System.out.println("In Sell Book:"+book);
        Optional<User> user = userService.getUserFromId(book.getUserId());
        if(user.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Command command = new SellBookCommand(book);
        Transaction transaction = new Transaction(Long.parseLong(System.currentTimeMillis()+""+myServer.getMyServerById(1).getPort()), command, "userService");
        return restService.post(restService.generateURL("localhost", 8080, "propose"), transaction);
    }

    @PostMapping("/shelf")
    public ResponseEntity<Book> shelfBook(@RequestBody Book book) {
        System.out.println("In Shelf Book:"+book);
        Optional<User> user = userService.getUserFromId(book.getUserId());
        if(user.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        bookService.shelfBook(book);
        return new ResponseEntity<>(bookService.getBook(book.getId()).get(), HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<Integer> deleteBook(@RequestBody Book book) {
        System.out.println("In Delete Book:"+book);
        Optional<User> user = userService.getUserFromId(book.getUserId());
        if(user.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        int id = book.getId();
        bookService.deleteBook(book);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<Book>> searchBook(@PathVariable("name") String name) {
        List<Book> list = bookService.searchBook(name);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/search/")
    public ResponseEntity<List<Book>> searchBook() {
        List<Book> list = bookService.searchBook("");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
