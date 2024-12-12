package com.example.arproject1.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.arproject1.Entities.OrderItems;
import com.example.arproject1.Entities.Orders;
import com.example.arproject1.Model.OrderDetails;
import com.example.arproject1.Model.OrdersAdminDetails;
import com.example.arproject1.Repository.OrdersRepository;
import com.example.arproject1.Repository.ProductsRepository;
import com.example.arproject1.Repository.UsersRepository;

@Service
public class OrdersServicesImpl implements OrdersServices {

    private final OrdersRepository ordersRepository;
    private final ProductsRepository productsRepository;
    private final UsersRepository usersRepository;

    public OrdersServicesImpl(OrdersRepository ordersRepository, ProductsRepository productsRepository,
            UsersRepository usersRepository) {
        this.ordersRepository = ordersRepository;
        this.productsRepository = productsRepository;
        this.usersRepository = usersRepository;
    }

    @SuppressWarnings("null")
    @Override
    public boolean addOrder(Orders orderModel) {

        ordersRepository.save(orderModel);

        return true;
    }

    @SuppressWarnings("null")
    @Override
    public boolean deleteOrder(long id) {

        Orders orders = ordersRepository.findById(id).get();
        ordersRepository.delete(orders);
        return true;

    }

    @Override
    public List<Orders> getAllOrders() {
        List<Orders> orders = ordersRepository.findAll();
        return orders;

    }

    @Override
    public Orders getOrderById(long id) {
        Orders orders = ordersRepository.findById(id).get();
        return orders;
    }

    @Override
    public boolean updateOrder(long id, long user_id, String order_date, String status, double total) {
        Orders orders = ordersRepository.findById(id).get();
        orders.setUser_id(user_id);
        orders.setOrder_date(order_date);
        orders.setStatus(status);
        orders.setTotal(total);
        ordersRepository.save(orders);
        return true;
    }

    @Override
    public List<Orders> getOrderByUserId(long user_id) {

        List<Orders> orders = ordersRepository.findAll();
        List<Orders> ordersList = new ArrayList<Orders>();

        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getUser_id() == user_id) {
                ordersList.add(orders.get(i));
            }
        }

        if (ordersList.size() > 0) {
            return ordersList;
        } else {
            return null;
        }
    }

    @Override
    public List<OrderDetails> getOrderDetailsById(long id) {
        Orders orders = ordersRepository.findById(id).get();
        List<OrderItems> orderItems = orders.getOrderItems();

        List<OrderDetails> orderDetails = new ArrayList<OrderDetails>();

        for (int i = 0; i < orderItems.size(); i++) {
            long item_id = orderItems.get(i).getItem_id();
            String name = productsRepository.findById(item_id).get().getName();
            String image = productsRepository.findById(item_id).get().getImage();
            String folderName = productsRepository.findById(item_id).get().getFolderName();
            OrderDetails orderDetail = new OrderDetails(orderItems.get(i).getId(), orderItems.get(i).getItem_id(),
                    orderItems.get(i).getQuantity(), orderItems.get(i).getPrice(), name, image, folderName);
            orderDetails.add(orderDetail);
        }

        if (orderDetails.size() > 0) {
            return orderDetails;
        } else {
            return null;
        }
    }

    @Override
    public List<OrdersAdminDetails> getAllOrdersAdmin() {
        List<Orders> orders = ordersRepository.findAll();
        List<OrdersAdminDetails> ordersAdminDetails = new ArrayList<OrdersAdminDetails>();

        for (int i = 0; i < orders.size(); i++) {
            long user_id = orders.get(i).getUser_id();
            String user_name = usersRepository.findById(user_id).get().getName();
            String user_email = usersRepository.findById(user_id).get().getEmail();
            String user_address = usersRepository.findById(user_id).get().getAddress();
            String user_phone = usersRepository.findById(user_id).get().getPhone();

            OrdersAdminDetails orderAdminDetail = new OrdersAdminDetails(
                    orders.get(i).getOrder_id(),
                    orders.get(i).getUser_id(),
                    orders.get(i).getOrder_date(),
                    orders.get(i).getStatus(),
                    orders.get(i).getTotal(),
                    null,
                    user_name,
                    user_email,
                    user_address,
                    user_phone);

            ordersAdminDetails.add(orderAdminDetail);

        }

        return ordersAdminDetails;
    }

    @Override
    public OrdersAdminDetails getOrderAdminById(long id) {
        Orders orders = ordersRepository.findById(id).get();
        long user_id = orders.getUser_id();
        String user_name = usersRepository.findById(user_id).get().getName();
        String user_email = usersRepository.findById(user_id).get().getEmail();
        String user_address = usersRepository.findById(user_id).get().getAddress();
        String user_phone = usersRepository.findById(user_id).get().getPhone();

        List<OrderItems> orderItems = orders.getOrderItems();
        List<OrderDetails> orderDetails = new ArrayList<OrderDetails>();

        for (int i = 0; i < orderItems.size(); i++) {
            long item_id = orderItems.get(i).getItem_id();
            String name = productsRepository.findById(item_id).get().getName();
            String image = productsRepository.findById(item_id).get().getImage();
            String folderName = productsRepository.findById(item_id).get().getFolderName();
            OrderDetails orderDetail = new OrderDetails(orderItems.get(i).getId(), orderItems.get(i).getItem_id(),
                    orderItems.get(i).getQuantity(), orderItems.get(i).getPrice(), name, image, folderName);
            orderDetails.add(orderDetail);
        }

        OrdersAdminDetails orderAdminDetail = new OrdersAdminDetails(
                orders.getOrder_id(),
                orders.getUser_id(),
                orders.getOrder_date(),
                orders.getStatus(),
                orders.getTotal(),
                orderDetails,
                user_name,
                user_email,
                user_address,
                user_phone);

        return orderAdminDetail;
    }

    @Override
    public boolean updateOrderStatus(long id, String status) {
        Orders orders = ordersRepository.findById(id).get();
        orders.setStatus(status);
        ordersRepository.save(orders);
        return true;
    }

}
