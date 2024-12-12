package com.example.arapp3.Model;

import java.util.List;

public class OrderModelServer {

    private int user_id;
    private List<CartModelServer> items;

    public OrderModelServer(int user_id, List<CartModelServer> items) {
        this.user_id = user_id;
        this.items = items;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public List<CartModelServer> getItems() {
        return items;
    }

    public void setItems(List<CartModelServer> items) {
        this.items = items;
    }
}
