package com.example.cs6650.server.distributedalgos.paxos;

import com.example.cs6650.server.controller.command.Command;
import com.example.cs6650.server.coordinator.RestService;
import com.example.cs6650.server.coordinator.Server;
import com.example.cs6650.server.distributedalgos.twophasecommit.Transaction;
import com.example.cs6650.server.distributedalgos.twophasecommit.TransactionExecutor;
import com.example.cs6650.server.repository.MyServerRepository;
import com.example.cs6650.server.service.BookService;
import com.example.cs6650.server.service.CartService;
import com.example.cs6650.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.*;

@Controller
public class PaxosController {
    
    @Autowired
    MyServerRepository myServer;

    @Autowired
    RestService restService;

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @Autowired
    CartService cartService;

    @Autowired
    PaxosRepository paxosRepository;

    private static final double FAILURE_CHANCE = 0.2;
    private static final int EXECUTOR_TIMEOUT = 10000; //10000 ms = 10 sec

    @PostMapping("/propose")
    public ResponseEntity<Object> propose(@RequestBody Transaction transaction) {

        int promisedServers = 0;

        long maxTransactionNumber = 0;
        Transaction currentTransaction = transaction;

        List<LinkedHashMap<String, Object>> serverList = (List<LinkedHashMap<String, Object>>) restService.post(restService.generateURL("localhost", 8080, "allservers"), null).getBody();
        List<Server> servers = new ArrayList<>();
        for (LinkedHashMap<String, Object> serverL: serverList) {
            Server server = new Server((String) serverL.get("host"), (Integer)serverL.get("port"));
            servers.add(server);
        }

        //Transaction Phase
        System.out.println("Proposing " + transaction);
        for (Server server: servers) {
            LinkedHashMap<String, Object> promiseL = (LinkedHashMap<String, Object>) restService.post(restService.generateURL(server.getHost(), server.getPort(), "prepare"), transaction).getBody();
            Promise promise = new Promise(promiseL);

            if (promise != null && promise.isPromise()) {
                promisedServers++;
                Transaction acceptedTransaction = promise.getTransaction();
                if (acceptedTransaction != null && acceptedTransaction.getId() > maxTransactionNumber) {
                    maxTransactionNumber = acceptedTransaction.getId();
                    currentTransaction = new Transaction(transaction.getId(), acceptedTransaction.getCommand(), acceptedTransaction.getServiceType());
                }
            }
        }

        if (promisedServers <= serverList.size() / 2) {
            System.out.println(transaction+ " failed to reach a consensus!");
            return new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);
        } else {
            System.out.println(transaction+ " reached a consensus!");
        }

        int numAccepted = 0;

        for (Server server : servers) {
            Long value = (Long) restService.post(restService.generateURL(server.getHost(), server.getPort(), "accept"), transaction).getBody();
            System.out.println(value);
            if (value != null && value == transaction.getId()) {
                numAccepted++;
            }
        }

        ResponseEntity<Object> response = new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);
        for (Server server : servers) {
            response = restService.post(restService.generateURL(server.getHost(), server.getPort(), "learn"), transaction);
        }
        return response;
    }

    @PostMapping("/prepare")
    public ResponseEntity<Object> prepare(@RequestBody Transaction transaction) {
        System.out.println("In Prepare");

        //Implementing random failure of the server with a probability of 0.1
//        if (Math.random() <= FAILURE_CHANCE) {
//            System.out.println("Got Preparation request for " + transaction + ": Failing Server");
//            return null;
//        }

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        FutureTask<ResponseEntity<Object>> futureTask = new FutureTask<>(new Callable<ResponseEntity<Object>>() {
            @Override
            public ResponseEntity<Object> call() throws Exception {
                Paxos paxos = paxosRepository.getPaxosById(1);
                if (transaction.getId() > paxos.getMinTransaction()) {
                    paxos.setMinTransaction(transaction.getId());
                    paxosRepository.save(paxos);
                    System.out.println("Got Preparation request for " + transaction + ": Promised");
                    return new ResponseEntity<>(new Promise(true, paxos.getTransaction()), HttpStatus.OK);
                } else {
                    System.out.println("Got Preparation request for " + transaction + ": Denied");
                    return new ResponseEntity<>(new Promise(false, null), HttpStatus.OK);
                }
            }
        });

        try {
            executorService.submit(futureTask);
            return futureTask.get(EXECUTOR_TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (ExecutionException | InterruptedException | TimeoutException e) {
            System.out.println("Timeout to prepare " + transaction + ": Failed Timeout");
        }
        return null;
    }

    @PostMapping("/accept")
    public ResponseEntity<Object> accept(@RequestBody Transaction transaction) throws RemoteException {

        //Implementing random failure of the server with a probability of 0.1
//        if (Math.random() <= FAILURE_CHANCE) {
//            System.out.println("Got Preparation request for " + transaction + ": Failing Server");
//            return null;
//        }

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        FutureTask<ResponseEntity<Object>> futureTask = new FutureTask<>((Callable<ResponseEntity<Object>>) () -> {
            Paxos paxos = paxosRepository.getPaxosById(1);
            if (transaction.getId() >= paxos.getMinTransaction()) {
                paxos.setMinTransaction(transaction.getId());
                paxos.setTransaction(new Transaction(transaction));
                paxosRepository.save(paxos);
                System.out.println("Got Accept request for " + transaction + ": Accepted");
            } else {
                System.out.println("Got Accept request for " + transaction + ": Rejected");
            }
            return new ResponseEntity<>(paxos.getMinTransaction(), HttpStatus.OK);
        });

        try {
            executorService.submit(futureTask);
            return futureTask.get(EXECUTOR_TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (ExecutionException | InterruptedException | TimeoutException e) {
            System.out.println("Timeout to accept " + transaction + ": Failed Timeout");
        }
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }


    // Implementation of paxos learning
    @PostMapping("/learn")
    public ResponseEntity<Object> learn(@RequestBody Transaction transaction){
//        Command command = transaction.getCommand();
        System.out.println("Got Learn request for " + transaction + ": Learned");
        resetPaxos();
        return TransactionExecutor.execute(transaction, userService, bookService, cartService);
    }

    private void resetPaxos() {
        Paxos paxos = paxosRepository.getPaxosById(1);
        paxos.setMinTransaction(0);
        paxos.setTransaction(null);
        paxosRepository.save(paxos);
    }
    
   
}
