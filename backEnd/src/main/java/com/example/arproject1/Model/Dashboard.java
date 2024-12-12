package com.example.arproject1.Model;

public class Dashboard {

    private int totalUsers;
    private int totalCategories;
    private int totalProducts;
    private int totalOrders;
    private int totalPendingOrders;
    private int totalProcessingOrders;
    private int totalCompletedOrders;
    private int totalCancelledOrders;

    public Dashboard() {
    }

    public Dashboard(int totalUsers, int totalCategories, int totalProducts, int totalOrders, int totalPendingOrders,
            int totalProcessingOrders, int totalCompletedOrders, int totalCancelledOrders) {
        this.totalUsers = totalUsers;
        this.totalCategories = totalCategories;
        this.totalProducts = totalProducts;
        this.totalOrders = totalOrders;
        this.totalPendingOrders = totalPendingOrders;
        this.totalProcessingOrders = totalProcessingOrders;
        this.totalCompletedOrders = totalCompletedOrders;
        this.totalCancelledOrders = totalCancelledOrders;
    }


    public int getTotalUsers() {
        return this.totalUsers;
    }

    public void setTotalUsers(int totalUsers) {
        this.totalUsers = totalUsers;
    }

    public int getTotalCategories() {
        return this.totalCategories;
    }

    public void setTotalCategories(int totalCategories) {
        this.totalCategories = totalCategories;
    }

    public int getTotalProducts() {
        return this.totalProducts;
    }

    public void setTotalProducts(int totalProducts) {
        this.totalProducts = totalProducts;
    }

    public int getTotalOrders() {
        return this.totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }

    public int getTotalPendingOrders() {
        return this.totalPendingOrders;
    }

    public void setTotalPendingOrders(int totalPendingOrders) {
        this.totalPendingOrders = totalPendingOrders;
    }

    public int getTotalProcessingOrders() {
        return this.totalProcessingOrders;
    }

    public void setTotalProcessingOrders(int totalProcessingOrders) {
        this.totalProcessingOrders = totalProcessingOrders;
    }

    public int getTotalCompletedOrders() {
        return this.totalCompletedOrders;
    }

    public void setTotalCompletedOrders(int totalCompletedOrders) {
        this.totalCompletedOrders = totalCompletedOrders;
    }

    public int getTotalCancelledOrders() {
        return this.totalCancelledOrders;
    }

    public void setTotalCancelledOrders(int totalCancelledOrders) {
        this.totalCancelledOrders = totalCancelledOrders;
    }



    
}