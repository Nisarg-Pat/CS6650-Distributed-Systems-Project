package com.example.cs6650.server.distributedalgos.twophasecommit;


import com.example.cs6650.server.controller.command.Command;
import com.example.cs6650.server.service.IService;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

public class Transaction implements Serializable {

    private final long id;
    private final Command command;
    private final String serviceType;

    public Transaction(long id, Command command, String serviceType) {
        this.id = id;
        this.command = command;
        this.serviceType = serviceType;
    }

    public Transaction(Transaction other) {
        this.id = other.getId();
        this.command = other.getCommand();
        this.serviceType = other.getServiceType();
    }

    public long getId() {
        return id;
    }

    public Command getCommand() {
        return command;
    }

    public String getServiceType() {
        return serviceType;
    }
}