package com.example.cs6650.server.model;

import java.util.List;

public class CartBody {
    private List<Book> bookList;
    private User newUser;

    public CartBody(List<Book> bookList, User newUser) {
        this.bookList = bookList;
        this.newUser = newUser;
    }

    public CartBody() {
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

    @Override
    public String toString() {
        return "CartBody{" +
                "bookList=" + bookList +
                ", newUser=" + newUser +
                '}';
    }
}
