package com.example.cs6650.server.distributedalgos.twophasecommit;


import com.example.cs6650.server.controller.command.Command;
import com.example.cs6650.server.service.IService;
import org.springframework.http.ResponseEntity;

public class Transaction {

    private final long id;
    private final Command command;

    public Transaction(long id, Command command) {
        this.id = id;
        this.command = command;
    }

    public long getId() {
        return id;
    }

    public Command getCommand() {
        return command;
    }

    public ResponseEntity<Object> execute(IService service) {
        return command.execute(service);
    }
}