package com.example.arproject1.Model;

import java.util.List;

public class OrderModel {
    private long user_id;
    private List<CartModel> cart;

    public OrderModel() {
    }

    public OrderModel(long user_id, List<CartModel> cart) {
        this.user_id = user_id;
        this.cart = cart;
    }

    public long getUser_id() {
        return this.user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public List<CartModel> getCart() {
        return this.cart;
    }

    public void setCart(List<CartModel> cart) {
        this.cart = cart;
    }


    
}
