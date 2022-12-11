package com.example.cs6650.server.repository;

import com.example.cs6650.server.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> getCartByUserId(int id);

    Optional<Cart> getCartByUserIdAndBookId(int userId, int bookId);
}
