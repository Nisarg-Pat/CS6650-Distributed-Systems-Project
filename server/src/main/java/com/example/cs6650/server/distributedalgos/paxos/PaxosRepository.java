package com.example.cs6650.server.distributedalgos.paxos;

import com.example.cs6650.server.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaxosRepository extends JpaRepository<Paxos, Integer> {
    Paxos getPaxosById(int id);
}
