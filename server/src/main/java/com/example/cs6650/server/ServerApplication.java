package com.example.cs6650.server;

import com.example.cs6650.server.model.Book;
import com.example.cs6650.server.model.User;
import com.example.cs6650.server.repository.BookRepository;
import com.example.cs6650.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
	}

    @Bean
    public CommandLineRunner run(UserRepository userRepository, BookRepository bookRepository) throws Exception {
        return (String[] args) -> {
            if(args.length ==1 && args[0].equals("--ds-coordinator")) {
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
            } else if(args.length == 2) {
                String host = args[0];
                String portString = args[1].split("=")[1];
                int port = Integer.parseInt(portString);

                String jsonInputString = "{\"host\": \""+host+"\", \"port\": "+port+"}";
                System.out.println(jsonInputString);

                URL url = new URL ("http://localhost:8080/addserver");
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json");
                con.setRequestProperty("Accept", "application/json");
                con.setDoOutput(true);

                try(OutputStream os = con.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }
                try(BufferedReader br = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine = null;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    System.out.println(response.toString());
                }
            }
        };
    }

}
