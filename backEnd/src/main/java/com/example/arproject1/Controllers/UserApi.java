package com.example.arproject1.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.arproject1.Entities.Orders;
import com.example.arproject1.Entities.Products;
import com.example.arproject1.Entities.UsersEntity;
import com.example.arproject1.Model.OrderDetails;
import com.example.arproject1.Services.OrdersServices;
import com.example.arproject1.Services.ProductsServices;
import com.example.arproject1.Services.UsersServices;
import com.example.arproject1.utils.Response;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController()
@RequestMapping("/users/api")
public class UserApi {

    private final ProductsServices productsServices;
    private final OrdersServices ordersServices;
    private final UsersServices usersServices;

    public UserApi(ProductsServices productsServices, OrdersServices ordersServices, UsersServices usersServices) {
        this.productsServices = productsServices;
        this.ordersServices = ordersServices;
        this.usersServices = usersServices;
    }

    @GetMapping("/product/get-all")
    public List<Products> getAllProducts() {

        List<Products> productsList = productsServices.getAllProduct();
        return productsList;

    }

    @PostMapping("/order/add")
    public Response addOrder(@RequestBody Orders orderModel) {


        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        orderModel.setOrder_date(timestamp.toString());
        orderModel.setStatus("pending");
        orderModel.getOrderItems().forEach((orderItem) -> {
            orderItem.setOrderTable(orderModel);
        });

        Double total = 0.0;
        for (int i = 0; i < orderModel.getOrderItems().size(); i++) {
            int quantity = orderModel.getOrderItems().get(i).getQuantity();
            double price = orderModel.getOrderItems().get(i).getPrice();
            total += quantity * price;
        }
        orderModel.setTotal(total);

        boolean result = ordersServices.addOrder(orderModel);

        if (result) {
            return new Response("Order added successfully", true);
        } else {
            return new Response("Order not added", false);
        }

    }

    @PostMapping("/order/delete")
    public Response deleteOrder(@RequestBody Orders order){

        long order_id = order.getOrder_id();
        Boolean isDelete = ordersServices.deleteOrder(order_id);
        Response res;
        if(isDelete){
            res = new Response("order Deleted", true);
        }else{
            res = new Response("Not Deleted", false);
        }
        

        return res;

    }
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    @GetMapping("/order/get-all")
    public List<Orders> getAllOrders() {

        List<Orders> ordersList = ordersServices.getAllOrders();
        return ordersList;

    }

    @PostMapping("/order/get-by-user-id")
    public List<Orders> getOrderByUserId(@RequestBody List<Orders> orderModel) {

        Orders orders = new Orders();
        for (int i = 0; i < orderModel.size(); i++) {
            System.out.println("User id: " + orderModel.get(i).getUser_id());
            orders.setUser_id(orderModel.get(i).getUser_id());
        }


        System.out.println("User id: " + orders.getUser_id());

        List<Orders> ordersList = ordersServices.getOrderByUserId(orders.getUser_id());
        return ordersList;

    }

    @GetMapping("/order/get-order-details-by-id")
    public List<OrderDetails> getOrderDetailsById(@Param("id") long id) {

        List<OrderDetails> orderDetailsList = ordersServices.getOrderDetailsById(id);
        return orderDetailsList;
    }

    @GetMapping("/user/profile")
    public UsersEntity getUserById(@Param("id") long id) {

        UsersEntity user = usersServices.getUserById(id);
        return user;
    }


    @PostMapping("/user/profile/update")
    public Response updateUserProfile(@RequestBody UsersEntity user) {

        boolean result = usersServices.updateUser(user.getId(), user.getName(), user.getEmail(), user.getPassword(),
                user.getPhone(), user.getAddress());

        if (result) {
            return new Response("User profile updated successfully", true);
        } else {
            return new Response("User profile not updated", false);
        }

    }

}
