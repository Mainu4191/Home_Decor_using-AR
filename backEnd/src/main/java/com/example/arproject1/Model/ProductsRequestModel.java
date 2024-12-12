package com.example.arproject1.Model;

import com.example.arproject1.utils.FileModelUtil;


public record ProductsRequestModel(
        long id,
        String name,
        String description,
        FileModelUtil image,
        FileModelUtil model,
        FileModelUtil modelBufferFile,
        String price,
        String productCategoryID) {
    public ProductsRequestModel {
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }
        if (description == null) {
            throw new IllegalArgumentException("description cannot be null");
        }
        if (image == null) {
            throw new IllegalArgumentException("image cannot be null");
        }
        if (model == null) {
            throw new IllegalArgumentException("model cannot be null");
        }

        if (modelBufferFile == null) {
            throw new IllegalArgumentException("modelBufferFile cannot be null");
        }
        
        if (price == null) {
            throw new IllegalArgumentException("price cannot be null");
        }
        if (productCategoryID == null) {
            throw new IllegalArgumentException("productCategoryID cannot be null");
        }
    }
}