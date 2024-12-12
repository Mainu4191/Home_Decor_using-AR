package com.example.arapp3.Model;

import java.util.ArrayList;
import java.util.List;

public class OrderModel {
    private long order_id;
    private long user_id;
    private String order_date;
    private String status;
    private double total;
    List<OrderItems> orderItems = new ArrayList<OrderItems>();

    public OrderModel(){}

    public OrderModel(long order_id, long user_id, String order_date, String status, double total) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.order_date = order_date;
        this.status = status;
        this.total = total;
    }

    public OrderModel(long order_id, long user_id, String order_date, String status, double total, List<OrderItems> orderItems) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.order_date = order_date;
        this.status = status;
        this.total = total;
        this.orderItems = orderItems;
    }

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<OrderItems> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItems> orderItems) {
        this.orderItems = orderItems;
    }

    public Object toJsonObject() {

        return
            "{" +
                "\"order_id\":" + order_id + "," +
                "\"user_id\":" + user_id + "," +
                "\"order_date\":\"" + order_date + "\"," +
                "\"status\":\"" + status + "\"," +
                "\"total\":" + total + "," +
                "\"orderItems\":" + orderItems +
            "}";

    }
}
