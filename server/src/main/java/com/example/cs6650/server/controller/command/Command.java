package com.example.cs6650.server.controller.command;

import com.example.cs6650.server.service.IService;
import org.springframework.http.ResponseEntity;
import java.io.Serializable;

/**
 * Command interface for executing an operation on the server.
 */
public interface Command extends Serializable {

    /**
     * The function specific to each command that it has to execute.
     * @param db The database(model) on which to perform execution
     * @return The return value specific to the command.
     */
    ResponseEntity<Object> execute(IService service);
}
