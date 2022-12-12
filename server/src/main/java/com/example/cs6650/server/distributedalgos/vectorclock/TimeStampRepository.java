package com.example.cs6650.server.distributedalgos.vectorclock;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeStampRepository extends JpaRepository<TimeStamp, Integer> {

    List<TimeStamp> findAll();

    TimeStamp getTimeStampById(int id);
}
