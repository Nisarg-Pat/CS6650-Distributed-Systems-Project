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
            Book book = new Book("a1", 1, 1, "shelf", 5.00);
            bookRepository.save(book);
        };
    }

}
