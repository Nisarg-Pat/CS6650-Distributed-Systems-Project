package com.example.cs6650.server.distributedalgos.twophasecommit;

import com.example.cs6650.server.controller.command.Command;
import com.example.cs6650.server.controller.command.LoginCommand;
import com.example.cs6650.server.controller.command.LogoutCommand;
import com.example.cs6650.server.controller.command.SignupCommand;
import com.example.cs6650.server.coordinator.RestService;
import com.example.cs6650.server.coordinator.Server;
import com.example.cs6650.server.model.User;
import com.example.cs6650.server.service.BookService;
import com.example.cs6650.server.service.CartService;
import com.example.cs6650.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.*;

@Controller
public class TwoPhaseCommitController {
    private Transaction currentTransaction = null;
    private ResponseEntity<Object> result = null;
    private Thread waitForTransactionResponse = null;
    private final Set<Long> committed = new HashSet<>();

    //For Transaction Manager
    private int serverCount;
    private int canCommitResponseCount;
    private int haveCommittedCount;
    private boolean startCommit;

    private static final Object TRANSACTION_LOCK = new Object();
    private static final int TRANSACTION_TIMEOUT = 10000; //10000 ms = 10 sec

    @Autowired
    RestService restService;

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @Autowired
    CartService cartService;

    public synchronized ResponseEntity<Object> performTransaction(Transaction transaction) {


        synchronized (TRANSACTION_LOCK) {
            transactionResetManager();
            result = null;
            currentTransaction = null;
            Thread transactionThread = new Thread(() -> {
                try {
                    //Getting the list of active servers.
                    List<LinkedHashMap<String, Object>> serverList = (List<LinkedHashMap<String, Object>>) restService.post(restService.generateURL("localhost", 8080, "allservers"), null).getBody();
                    List<Server> canCommitList = new ArrayList<>();
                    System.out.println(serverList);
                    serverCount = serverList.size();

                    for(int i=0;i<serverList.size();i++) {
                        System.out.println(serverList.get(i));
                        System.out.println(serverList.get(i).get("host"));
                        System.out.println(serverList.get(i).get("port"));
                    }

                    //Voting phase
                    for(LinkedHashMap<String, Object> serverL: serverList) {
                        Server server = new Server((String) serverL.get("host"), (Integer)serverL.get("port"));
                        System.out.println(server);
                        boolean res = (Boolean) restService.post(restService.generateURL(server.getHost(), server.getPort(), "cancommit"), transaction).getBody();
                        if(res) {
                            canCommitList.add(server);
                        }
                        canCommitResponseCount++;
                    }

                    System.out.println("Voting Phase Finished");

                    //Completion according to outcome phase
                    if(canCommitList.size() == serverList.size()) {
                        //doCommit
                        startCommit = true;
                        for(Server server: canCommitList) {
                            result = restService.post(restService.generateURL(server.getHost(), server.getPort(), "docommit"), transaction);
                        }
                    } else {
                        //doAbort
                        for(Server server: canCommitList) {
                            restService.post(restService.generateURL(server.getHost(), server.getPort(), "doabort"), transaction);
                        }
                    }
                    transactionResetManager();

                } catch (Exception e) {
                    e.printStackTrace();
                    currentTransaction = null;
                    result = null;
                    transactionResetManager();
                }
            });
//          //Timeout mechanism
            long timeoutTime = System.currentTimeMillis() + TRANSACTION_TIMEOUT;
            transactionThread.start();
            while (transactionThread.isAlive()) {
                long currentTime = System.currentTimeMillis();
                if (currentTime >= timeoutTime) {
                    currentTransaction = null;
                    result = null;
                    transactionThread.interrupt();
                    System.out.println("Breaking");
                    break;
                }
            }
            System.out.println("Breeaking from Loop");
            return result;
        }
    }

    @PostMapping("/cancommit")
    public ResponseEntity<Object> canCommit(@RequestBody Transaction transaction) {
        if(transaction == null) {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
        while(currentTransaction != null) {
            continue;
        }
        //In memory copy of the database
        currentTransaction = transaction;

        if(currentTransaction!=null) {
            System.out.println("canCommit "+transaction.getId()+": true");
            waitForTransactionResponse = new Thread(() -> {
                try {
                    Thread.sleep(TRANSACTION_TIMEOUT);
                    boolean result = (boolean) restService.post(restService.generateURL("localhost", 8080, "getdecision"), transaction).getBody();
                    if(result) {
                        doCommit(transaction);
                    } else {
                        doAbort(transaction);
                    }
                } catch (InterruptedException e) {
                    //Empty
                }
            });
            waitForTransactionResponse.start();
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            //Cannot commit the transaction. Abort immediately
            System.out.println("canCommit "+transaction.getId()+": false");
            currentTransaction = null;
            result = null;
            return new ResponseEntity<>(false, HttpStatus.OK);
        }

    }

    @PostMapping("/docommit")
    public ResponseEntity<Object> doCommit(@RequestBody Transaction transaction) {
        System.out.println("doCommit: "+transaction.getId());
        if(committed.contains(transaction.getId())) {
            System.out.println("Already Committed: "+transaction.getId());
            return new ResponseEntity<>( null, HttpStatus.OK);
        }
        committed.add(transaction.getId());

        ResponseEntity<Object> res = TransactionExecutor.execute(transaction, userService, bookService, cartService);
//        if(transaction.getServiceType().equals("userService")) {
//            result = transaction.execute(userService).getBody();
//        }
//        else if(transaction.getServiceType().equals("bookService")) {
//            result = transaction.execute(bookService).getBody();
//        }

        if(waitForTransactionResponse!=null && waitForTransactionResponse.isAlive()) {
            waitForTransactionResponse.interrupt();
            waitForTransactionResponse = null;
        }
        currentTransaction = null;
        //Send a haveCommited message to Manager.
        System.out.println("Sending transaction to haveCommitted");
        restService.post(restService.generateURL("localhost", 8080, "havecommitted"), transaction);
        return res;
    }

    @PostMapping("/doabort")
    public ResponseEntity<Object> doAbort(@RequestBody Transaction transaction) {
        System.out.println("doAbort: "+transaction.getId());
        result = null;
        if(waitForTransactionResponse!=null && waitForTransactionResponse.isAlive()) {
            waitForTransactionResponse.interrupt();
            waitForTransactionResponse = null;
        }
        currentTransaction = null;
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping("/havecommitted")
    public ResponseEntity<Object> haveCommitted(@RequestBody Transaction transaction) {
        //Received a havec ommited message, increment the counter.
        System.out.println("haveCommitted: "+transaction.getId());
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping("/getdecision")
    public ResponseEntity<Object> getDecision(@RequestBody Transaction transaction){
        //Server asking for a decision to commit or not to manager.
        System.out.println("getDecision: "+transaction.getId());
        while(canCommitResponseCount != serverCount) {
            continue;
        }
        return new ResponseEntity<>(startCommit, HttpStatus.OK);
    }

    private void transactionResetManager() {
        serverCount = 0;
        canCommitResponseCount = 0;
        startCommit = false;
    }
}
