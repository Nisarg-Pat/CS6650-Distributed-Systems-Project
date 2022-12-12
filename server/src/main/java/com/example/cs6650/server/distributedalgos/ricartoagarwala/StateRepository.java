package com.example.cs6650.server.distributedalgos.ricartoagarwala;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {
    State getStateById(int id);
}
