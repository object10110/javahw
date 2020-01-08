package org.samarskii.domain;

public class Token {
    private int id;
    private int buyerId;
    private String token;

    public Token(int id, int buyerId, String token) {
        this.id = id;
        this.buyerId = buyerId;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
