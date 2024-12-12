package com.example.arproject1.Model;

public class OrderDetails {

    private long id;
    private long item_id;
    private int quantity;
    private double price;
    private String name;
    private String image;
    private String folderName;

    public OrderDetails() {
    }

    public OrderDetails(long id, long item_id, int quantity, double price, String name, String image) {
        super();
        this.id = id;
        this.item_id = item_id;
        this.quantity = quantity;
        this.price = price;
        this.name = name;
        this.image = image;
    }

    public OrderDetails(long id, long item_id, int quantity, double price, String name, String image,
            String folderName) {
        super();
        this.id = id;
        this.item_id = item_id;
        this.quantity = quantity;
        this.price = price;
        this.name = name;
        this.image = image;
        this.folderName = folderName;
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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFolderName() {
        return this.folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

}
