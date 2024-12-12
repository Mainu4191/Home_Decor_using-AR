package com.example.arproject1.Services;

import java.util.List;

import com.example.arproject1.Entities.Products;

public interface ProductsServices {
    // Products-Start
    // Product Add
    // Product Update
    // Product Delete
    // Product Get All
    // Product Get By Id
    // Products-End

    boolean createProduct(String name, String description, String image, String productCategoryID, String model,
    String modelBufferFile, String folderName,
            String price);

    boolean updateProduct(long id, String name, String description, String image, String productCategoryID,
            String model, String price);

    boolean deleteProduct(long id);

    List<Products> getAllProduct();

    Products getProductById(long id);

}
