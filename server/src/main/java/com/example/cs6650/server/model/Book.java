package com.example.cs6650.server.model;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private int userId;
    private String status;
    private double sellPrice;

    public Book() {

    }

    public Book(@NonNull String name, @NonNull int userId, @NonNull String status, @NonNull double sellPrice) {
        this.name = name;
        this.userId = userId;
        this.status = status;
        this.sellPrice = sellPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userId=" + userId +
                ", status='" + status + '\'' +
                ", sellCost=" + sellPrice +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public void copyFrom(Book book) {
        setName(book.getName());
        setUserId(book.getUserId());
        setStatus(book.getStatus());
        setSellPrice(book.getSellPrice());
    }
}
