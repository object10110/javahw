package org.samarskii.domain;

public class BasketItem {
    private int id;
    private int buyerId;
    private int productId;
    private int amount;

    public BasketItem() {}

    public BasketItem(int id, int buyerId, int productId, int amount) {
        this.id = id;
        this.buyerId = buyerId;
        this.productId = productId;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
