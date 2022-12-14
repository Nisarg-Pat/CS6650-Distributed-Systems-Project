package com.example.cs6650.server.distributedalgos.twophasecommit;


import com.example.cs6650.server.controller.command.Command;
import com.example.cs6650.server.service.IService;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

public class Transaction implements Serializable {

    private final long id;
    private final Command command;

    public Transaction(long id, Command command) {
        this.id = id;
        this.command = command;
    }

    public Transaction(Transaction other) {
        this.id = other.getId();
        this.command = other.getCommand();
    }

    public long getId() {
        return id;
    }

    public Command getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", command=" + command +
                '}';
    }
}