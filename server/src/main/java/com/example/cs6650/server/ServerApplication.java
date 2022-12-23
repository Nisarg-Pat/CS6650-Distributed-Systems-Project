package com.example.cs6650.server;

import com.example.cs6650.server.common.Log;
import com.example.cs6650.server.distributedalgos.paxos.Paxos;
import com.example.cs6650.server.distributedalgos.paxos.PaxosRepository;
import com.example.cs6650.server.distributedalgos.ricartoagarwala.State;
import com.example.cs6650.server.distributedalgos.ricartoagarwala.StateRepository;
import com.example.cs6650.server.distributedalgos.vectorclock.TimeStamp;
import com.example.cs6650.server.distributedalgos.vectorclock.TimeStampRepository;
import com.example.cs6650.server.model.Book;
import com.example.cs6650.server.model.MyServer;
import com.example.cs6650.server.model.User;
import com.example.cs6650.server.repository.BookRepository;
import com.example.cs6650.server.repository.MyServerRepository;
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
    public CommandLineRunner run(UserRepository userRepository, BookRepository bookRepository, MyServerRepository myServerRepository, PaxosRepository paxosRepository, StateRepository stateRepository, TimeStampRepository timeStampRepository) throws Exception {
        return (String[] args) -> {
            String host="localhost";
            int port=8080;
            int index = -1;
            if(args.length ==1 && args[0].equals("--ds-coordinator")) {
                User user = new User("user1","user1","user1","user1");
                userRepository.save(user);
                Book book = new Book("Introduction to Algorithms", 1, "shelf", 0);
                bookRepository.save(book);
                book = new Book("Distributed Systems Concepts and Design", 1, "sell", 10.0);
                bookRepository.save(book);
                book = new Book("Operating Systems in Three Easy Pieces", 1, "sell", 15.0);
                bookRepository.save(book);
                book = new Book("Harry Potter", 1, "sell", 20.0);
                bookRepository.save(book);

                User user2 = new User("user2","user2","user2","user2");
                userRepository.save(user2);
                book = new Book("Introduction to Algorithms", 2, "shelf", 0);
                bookRepository.save(book);
                book = new Book("Introduction to Computer Science", 2, "shelf", 0);
                bookRepository.save(book);
                book = new Book("Research Methods in Education", 2, "sell", 3);
                bookRepository.save(book);
                book = new Book("Gateway to India", 2, "sell", 22);
                bookRepository.save(book);

                User user3 = new User("user3","user2","user2","user2");
                userRepository.save(user3);
                book = new Book("Paxos Algorithm", 3, "shelf", 0);
                bookRepository.save(book);
                book = new Book("World War 3", 3, "sell", 21);
                bookRepository.save(book);
                book = new Book("Introduction to Algorithms", 3, "sell", 31);
                bookRepository.save(book);
                book = new Book("History of Data", 3, "sell", 41);
                bookRepository.save(book);

                host = "localhost";
                port = 8080;

            } else if(args.length == 2) {
                host = args[0];
                String portString = args[1].split("=")[1];
                port = Integer.parseInt(portString);
            }

            String jsonInputString = "{\"host\": \""+host+"\", \"port\": "+port+"}";
            Log.logln(jsonInputString);

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
                Log.logln(response.toString());
                String ss = response.toString().split("\"id\":")[1].split(",")[0];
                index = Integer.parseInt(ss);
            }

            myServerRepository.save(new MyServer(host, port, index));
            paxosRepository.save(new Paxos(0, null));
            for(int i=0;i<100;i++) {
                TimeStamp timeStamp = new TimeStamp( 0);
                timeStampRepository.save(timeStamp);
            }

            State state = new State();
            state.setState(State.RELEASED);
            stateRepository.save(state);
        };
    }

}
