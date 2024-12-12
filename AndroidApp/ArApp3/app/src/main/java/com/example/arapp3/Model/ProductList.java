package com.example.arapp3.Model;

public class ProductList {
    private long id;
    private String name;
    private String description;
    private String image;
    private String model;

    private String modelBufferFile;
    private String folderName;
    private String price;
    private String createdAt;
    private String updatedAt;
    private String productCategoryID;

    public ProductList() {
    }

    public ProductList(long id, String name, String description, String image, String model, String price, String createdAt, String updatedAt, String productCategoryID) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.model = model;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.productCategoryID = productCategoryID;
    }

    public ProductList(long id, String name, String description, String image, String model, String modelBufferFile, String folderName, String price, String createdAt, String updatedAt, String productCategoryID) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.model = model;
        this.modelBufferFile = modelBufferFile;
        this.folderName = folderName;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.productCategoryID = productCategoryID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getProductCategoryID() {
        return productCategoryID;
    }

    public void setProductCategoryID(String productCategoryID) {
        this.productCategoryID = productCategoryID;
    }


    public String getModelBufferFile() {
        return modelBufferFile;
    }

    public void setModelBufferFile(String modelBufferFile) {
        this.modelBufferFile = modelBufferFile;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", description='" + getDescription() + "'" +
                ", image='" + getImage() + "'" +
                ", model='" + getModel() + "'" +
                ", price='" + getPrice() + "'" +
                ", createdAt='" + getCreatedAt() + "'" +
                ", updatedAt='" + getUpdatedAt() + "'" +
                ", productCategoryID='" + getProductCategoryID() + "'" +
                "}";
    }
}
