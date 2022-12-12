package com.example.cs6650.server.distributedalgos.vectorclock;

import jakarta.persistence.*;

@Entity
@Table(name = "timestamp")
public class TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int val;

    public TimeStamp(int val) {
        this.val = val;
    }

    public TimeStamp() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }
}
