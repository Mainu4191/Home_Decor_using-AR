package com.example.arproject1.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.arproject1.Entities.OrderItems;
import com.example.arproject1.Repository.OrdersItemsRepository;

@Service
public class OrderItemsServicesImpl implements OrderItemsServices {

    private final OrdersItemsRepository ordersItemsRepository;

    public OrderItemsServicesImpl(OrdersItemsRepository ordersItemsRepository) {
        this.ordersItemsRepository = ordersItemsRepository;
    }

    @Override
    public boolean addOrderItems(long order_id, long product_id, int quantity, double price) {
        OrderItems orderItems = new OrderItems();
        orderItems.setItem_id(quantity);
        orderItems.setQuantity(quantity);
        orderItems.setPrice(price);
        ordersItemsRepository.save(orderItems);
        return true;
    }

    @SuppressWarnings("null")
    @Override
    public boolean deleteOrderItems(long id) {

        OrderItems orderItems = ordersItemsRepository.findById(id).get();
        ordersItemsRepository.delete(orderItems);
        return true;
    }

    @Override
    public List<OrderItems> getAllOrderItems() {

        List<OrderItems> orderItems = ordersItemsRepository.findAll();
        return orderItems;
    }

    @Override
    public OrderItems getOrderItemsById(long id) {

        OrderItems orderItems = ordersItemsRepository.findById(id).get();
        return orderItems;
    }

    @Override
    public boolean updateOrderItems(long id, long order_id, long product_id, int quantity, double price) {
        OrderItems orderItems = ordersItemsRepository.findById(id).get();
        orderItems.setItem_id(product_id);
        orderItems.setQuantity(quantity);
        orderItems.setPrice(price);
        ordersItemsRepository.save(orderItems);
        return true;
    }

}
