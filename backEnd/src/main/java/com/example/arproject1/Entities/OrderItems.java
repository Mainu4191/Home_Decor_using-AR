package com.example.arproject1.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@SuppressWarnings("unused")
@Entity
@Table(name = "order_items")
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long item_id;
    private int quantity;
    private double price;



    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonIgnore
    Orders orderTable;

    public OrderItems() {
    }

    public OrderItems(long id, long item_id, int quantity, double price) {
        super();
        this.id = id;
        this.item_id = item_id;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderItems(long id, long item_id, int quantity, double price, Orders orderTable) {
        super();
        this.id = id;
        this.item_id = item_id;
        this.quantity = quantity;
        this.price = price;
        this.orderTable = orderTable;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getItem_id() {
        return this.item_id;
    }

    public void setItem_id(long item_id) {
        this.item_id = item_id;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Orders getOrderTable() {
        return this.orderTable;
    }

    public void setOrderTable(Orders orderTable) {
        this.orderTable = orderTable;
    }

}
