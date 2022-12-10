package com.example.cs6650.server;

import com.example.cs6650.server.model.Book;
import com.example.cs6650.server.model.User;
import com.example.cs6650.server.repository.BookRepository;
import com.example.cs6650.server.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

    @Bean
    public CommandLineRunner run(UserRepository userRepository, BookRepository bookRepository) throws Exception {
        return (String[] args) -> {
            User user = new User("qq","qq","qq","qq");
            userRepository.save(user);
            Book book = new Book("a1", 1, "shelf", 0);
            bookRepository.save(book);
            book = new Book("a2", 1, "sell", 10.0);
            bookRepository.save(book);
            book = new Book("a3", 1, "sell", 15.0);
            bookRepository.save(book);
            book = new Book("a4", 1, "sell", 20.0);
            bookRepository.save(book);

            User user2 = new User("user2","user2","user2","user2");
            userRepository.save(user2);
            book = new Book("b1", 2, "shelf", 0);
            bookRepository.save(book);
            book = new Book("b2", 2, "shelf", 0);
            bookRepository.save(book);
            book = new Book("b3", 2, "sell", 3);
            bookRepository.save(book);
            book = new Book("b4", 2, "sell", 22);
            bookRepository.save(book);

            User user3 = new User("user3","user2","user2","user2");
            userRepository.save(user3);
            book = new Book("c1", 3, "shelf", 0);
            bookRepository.save(book);
            book = new Book("c2", 3, "sell", 21);
            bookRepository.save(book);
            book = new Book("c3", 3, "sell", 31);
            bookRepository.save(book);
            book = new Book("c4", 3, "sell", 41);
            bookRepository.save(book);
        };
    }

}
