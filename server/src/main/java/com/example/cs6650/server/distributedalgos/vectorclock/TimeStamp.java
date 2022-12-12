package com.example.cs6650.server.distributedalgos.vectorclock;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "timestamp")
public class TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int value;

    public TimeStamp(int value) {
        this.value = value;
    }

    public TimeStamp() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
