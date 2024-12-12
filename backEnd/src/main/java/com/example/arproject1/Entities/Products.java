package com.example.arproject1.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String image;
    private String model;
    private String modelBufferFile;
    private String folderName;
    private String price;
    private String createdAt;
    private String updatedAt;
    private String productCategoryID;

    public Products() {
    }

    public Products(long id, String name, String description, String image, String model, String modelBufferFile,
            String folderName, String price, String createdAt, String updatedAt, String productCategoryID) {
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
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModelBufferFile() {
        return this.modelBufferFile;
    }

    public void setModelBufferFile(String modelBufferFile) {
        this.modelBufferFile = modelBufferFile;
    }

    public String getFolderName() {
        return this.folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getproductCategoryID() {
        return this.productCategoryID;
    }

    public void setproductCategoryID(String productCategoryID) {
        this.productCategoryID = productCategoryID;
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
                ", productCategoryID='" + getproductCategoryID() + "'" +
                "}";
    }

}