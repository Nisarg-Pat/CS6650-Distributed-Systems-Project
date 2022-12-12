package com.example.cs6650.server.repository;

import com.example.cs6650.server.model.MyServer;
import com.example.cs6650.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyServerRepository extends JpaRepository<MyServer, Integer> {
    MyServer getMyServerById(int id);
}
