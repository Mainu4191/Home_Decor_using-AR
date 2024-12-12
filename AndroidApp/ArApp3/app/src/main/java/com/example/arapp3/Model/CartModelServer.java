package com.example.arapp3.Model;

public class CartModelServer {
    private int item_id;
    private int quantity;
    private int price;

    public CartModelServer(int product_id, int quantity, int price) {
        this.item_id = product_id;
        this.quantity = quantity;
        this.price = price;
    }

    public int getProduct_id() {
        return item_id;
    }

    public void setProduct_id(int product_id) {
        this.item_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
