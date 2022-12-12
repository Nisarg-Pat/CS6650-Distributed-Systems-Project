package com.example.cs6650.server.distributedalgos.ricartoagarwala;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class State {
    public static final String RELEASED = "Released";
    public static final String WANTED = "Wanted";
    public static final String HELD = "HELD";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String state;
    private String userState;
    private String bookState;
    private String cartState;
    private long timestamp;
    private int responseCount;
    private int serverCount;


    public State(String state, String userState, String bookState, String cartState, long timestamp) {
        this.state = state;
        this.userState = userState;
        this.bookState = bookState;
        this.cartState = cartState;
        this.timestamp = timestamp;
        this.responseCount = 0;
        this.serverCount = 0;
    }

    public State() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getBookState() {
        return bookState;
    }

    public void setBookState(String bookState) {
        this.bookState = bookState;
    }

    public String getCartState() {
        return cartState;
    }

    public void setCartState(String cartState) {
        this.cartState = cartState;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "State{" +
                "id=" + id +
                ", state='" + state + '\'' +
                ", userState='" + userState + '\'' +
                ", bookState='" + bookState + '\'' +
                ", cartState='" + cartState + '\'' +
                '}';
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getResponseCount() {
        return responseCount;
    }

    public void setResponseCount(int responseCount) {
        this.responseCount = responseCount;
    }

    public int getServerCount() {
        return serverCount;
    }

    public void setServerCount(int serverCount) {
        this.serverCount = serverCount;
    }
}
