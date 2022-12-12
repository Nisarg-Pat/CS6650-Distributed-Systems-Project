package com.example.cs6650.server.controller.command;

import com.example.cs6650.server.model.Book;
import com.example.cs6650.server.model.Cart;
import com.example.cs6650.server.model.CartBody;
import com.example.cs6650.server.model.User;
import com.example.cs6650.server.service.IService;
import org.springframework.http.ResponseEntity;
import java.io.Serializable;

/**
 * Command interface for executing an operation on the server.
 */
public class Command{
    User user;
    Book book;
    Cart cart;
    CartBody cartBody;
    String type;
    public Command(User user, Book book, Cart cart, CartBody cartBody, String type) {
        this.user = user;
        this.book = book;
        this.cart = cart;
        this.cartBody = cartBody;
        this.type = type;
    }

    public Command() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public CartBody getCartBody() {
        return cartBody;
    }

    public void setCartBody(CartBody cartBody) {
        this.cartBody = cartBody;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Command{" +
                "user=" + user +
                ", book=" + book +
                ", cart=" + cart +
                ", cartBody=" + cartBody +
                ", type='" + type + '\'' +
                '}';
    }
}
