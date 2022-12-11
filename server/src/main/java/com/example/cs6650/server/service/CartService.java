package com.example.cs6650.server.service;

import com.example.cs6650.server.model.Book;
import com.example.cs6650.server.model.Cart;
import com.example.cs6650.server.model.User;
import com.example.cs6650.server.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;


    public List<Cart> getCartOfUser(User user) {
        return cartRepository.getCartByUserId(user.getId());
    }

    public void addCart(Cart cart) {
        cartRepository.save(cart);
    }

    public void removeCart(Cart cart) {
        cartRepository.delete(cart);
    }

    public void removeAll(Integer bookId) {
        cartRepository.deleteAllByBookId(bookId);
    }

    public Optional<Cart> getCart(Cart cart) {
        return cartRepository.getCartByUserIdAndBookId(cart.getUserId(), cart.getBookId());
    }

    public List<Cart> allCarts() {
        return cartRepository.findAll();
    }
}
