package com.example.arapp3.Model;

public class CartModel {
//  "product_id, name, price, description, image, quantity"

    int CartID;
    int product_id;
    String name;
    int price;
    String description;
    String image;

    String folderName;
    int quantity;

    public CartModel(int cartID, int product_id, String name, int price, String description, String image, int quantity) {
        CartID = cartID;
        this.product_id = product_id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.quantity = quantity;
    }

    public CartModel(int cartID, int product_id, String name, int price, String description, String image, String folderName, int quantity) {
        CartID = cartID;
        this.product_id = product_id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.folderName = folderName;
        this.quantity = quantity;
    }
    public CartModel(int product_id, String name, int price, String description, String image, int quantity) {
        this.product_id = product_id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.quantity = quantity;
    }

    public CartModel() {
    }

    public int getCartID() {
        return CartID;
    }

    public void setCartID(int cartID) {
        CartID = cartID;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    //getTotal()
    public int getTotal(){
        return this.price * this.quantity;
    }
}
