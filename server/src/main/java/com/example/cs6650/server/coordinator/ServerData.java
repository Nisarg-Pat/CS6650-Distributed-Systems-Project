package com.example.cs6650.server.coordinator;

import com.example.cs6650.server.model.Book;
import com.example.cs6650.server.model.Cart;
import com.example.cs6650.server.model.User;

import java.util.List;

public class ServerData {
    private List<User> users;
    private List<Cart> carts;
    private List<Book> books;

    public ServerData(List<User> users, List<Cart> carts, List<Book> books) {
        this.users = users;
        this.carts = carts;
        this.books = books;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "ServerData{" +
                "users=" + users +
                ", carts=" + carts +
                ", books=" + books +
                '}';
    }
}
