package com.example.arproject1.Model;

import java.util.List;

import com.example.arproject1.Entities.Orders;

public class OrdersAdminDetails extends Orders {

    private String user_name;
    private String user_email;
    private String user_address;
    private String user_phone;
    private List<OrderDetails> orderDetails;

    public OrdersAdminDetails() {
        super();
    }

    public OrdersAdminDetails(long order_id, long user_id, String order_date, String status, double total,
            List<OrderDetails> orderItems, String user_name, String user_email, String user_address,
            String user_phone) {
        super(order_id, user_id, order_date, status, total);
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_address = user_address;
        this.user_phone = user_phone;
        this.orderDetails = orderItems;
        // this.setOrderItems(orderItems);

    }

    public String getUser_name() {
        return this.user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return this.user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_address() {
        return this.user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getUser_phone() {
        return this.user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public List<OrderDetails> getOrderDetails() {
        return this.orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

}
