package com.example.arproject1.Services;

import java.util.List;

import com.example.arproject1.Entities.OrderItems;

public interface OrderItemsServices {


    boolean addOrderItems(long order_id, long product_id, int quantity, double price);
    List<OrderItems> getAllOrderItems();
    OrderItems getOrderItemsById(long id);
    boolean updateOrderItems(long id, long order_id, long product_id, int quantity, double price);
    boolean deleteOrderItems(long id);
    
}
