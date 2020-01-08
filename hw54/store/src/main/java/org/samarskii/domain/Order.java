package org.samarskii.domain;

public class Order {
    private int id;
    private int productId;

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    private  int buyerId;
    private int sellerId;
    private int amount;

    public Order(){};

    public Order(int id, int buyerId, int sellerId, int productId, int amount) {
        this.id = id;
        this.buyerId = buyerId;
        this.productId = productId;
        this.sellerId = sellerId;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
