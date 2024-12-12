package com.example.arapp3.Model;

public class OrderItems {
    private long id;
    private long item_id;
    private int quantity;
    private double price;

    public OrderItems(){}

    public OrderItems(long item_id, int quantity, double price) {
        this.item_id = item_id;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderItems(long id, long item_id, int quantity, double price) {
        this.id = id;
        this.item_id = item_id;
        this.quantity = quantity;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getItem_id() {
        return item_id;
    }

    public void setItem_id(long item_id) {
        this.item_id = item_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
