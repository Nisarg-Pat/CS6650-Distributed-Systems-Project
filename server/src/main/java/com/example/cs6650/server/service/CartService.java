package com.example.cs6650.server.service;

import com.example.cs6650.server.model.Book;
import com.example.cs6650.server.model.Cart;
import com.example.cs6650.server.model.User;
import com.example.cs6650.server.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
