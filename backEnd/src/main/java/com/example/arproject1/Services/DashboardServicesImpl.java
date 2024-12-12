package com.example.arproject1.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.arproject1.Entities.Orders;
import com.example.arproject1.Model.Dashboard;

@Service
public class DashboardServicesImpl implements DashboardServices {

    private final UsersServices usersServices;
    private final ProductCategoryService CategoryServices;
    private final ProductsServices productsServices;
    private final OrdersServices ordersServices;

    public DashboardServicesImpl(UsersServices usersServices, ProductCategoryService CategoryServices,
            ProductsServices productsServices, OrdersServices ordersServices) {
        this.usersServices = usersServices;
        this.CategoryServices = CategoryServices;
        this.productsServices = productsServices;
        this.ordersServices = ordersServices;
    }

    @Override
    public Dashboard getDashboardData() {
        Dashboard dashboard = new Dashboard();

        int totalUsers = usersServices.getAllUser().size();
        int totalCategories = CategoryServices.getAllProductCategory().size();
        int totalProducts = productsServices.getAllProduct().size();
        int totalOrders = ordersServices.getAllOrders().size();

        List<Orders> AllOrders = ordersServices.getAllOrders();
        int totalPendingOrders = 0;
        int totalProcessingOrders = 0;
        int totalCompletedOrders = 0;
        int totalCancelledOrders = 0;
        for (Orders order : AllOrders) {
            if (order.getStatus().equals("pending")) {
                totalPendingOrders++;
            } else if (order.getStatus().equals("Processing")) {
                totalProcessingOrders++;
            } else if (order.getStatus().equals("Completed")) {
                totalCompletedOrders++;
            } else if (order.getStatus().equals("Cancelled")) {
                totalCancelledOrders++;
            }
        }

        dashboard.setTotalUsers(totalUsers);
        dashboard.setTotalCategories(totalCategories);
        dashboard.setTotalProducts(totalProducts);
        dashboard.setTotalOrders(totalOrders);
        dashboard.setTotalPendingOrders(totalPendingOrders);
        dashboard.setTotalProcessingOrders(totalProcessingOrders);
        dashboard.setTotalCompletedOrders(totalCompletedOrders);
        dashboard.setTotalCancelledOrders(totalCancelledOrders);

        return dashboard;

    }

}
