package com.example.arproject1.Model;

public class ProductCategoryRequestModel {

    private String name;

    private String description;

    public ProductCategoryRequestModel() {
    }

    public ProductCategoryRequestModel(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
