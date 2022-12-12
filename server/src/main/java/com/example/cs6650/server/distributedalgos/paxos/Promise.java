package com.example.cs6650.server.distributedalgos.paxos;

import com.example.cs6650.server.distributedalgos.twophasecommit.Transaction;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * A representation of a Promise sent by Acceptor to Proposer during Paxos.
 */
public class Promise{
    private boolean promise;
    private Transaction transaction;

    public Promise(boolean promise, Transaction transaction) {
        this.promise = promise;
        this.transaction = transaction;
    }

    public Promise(LinkedHashMap<String, Object> map) {
        this.promise = (boolean) map.get("promise");
        this.transaction = (Transaction) map.get("transaction");
    }

    public Promise() {
    }

    public boolean isPromise() {
        return promise;
    }

    public void setPromise(boolean promise) {
        this.promise = promise;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public String toString() {
        return "Promise{" +
                "promise=" + promise +
                ", transaction=" + transaction +
                '}';
    }
}
