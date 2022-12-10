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
    private int quantity;
    private int userId;
    private String status;
    private double sellCost;

    public Book() {

    }

    public Book(@NonNull String name, @NonNull int quantity, @NonNull int userId, @NonNull String status, @NonNull double sellCost) {
        this.name = name;
        this.quantity = quantity;
        this.userId = userId;
        this.status = status;
        this.sellCost = sellCost;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Book {name="+name+", quantity="+quantity+"}";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getSellCost() {
        return sellCost;
    }

    public void setSellCost(double sellCost) {
        this.sellCost = sellCost;
    }
}
