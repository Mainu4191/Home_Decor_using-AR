package com.example.arproject1.Services;

import java.util.List;

import com.example.arproject1.Entities.ProductCategory;

public interface ProductCategoryService{

    
    //ProductCategory-Start
    //Product Category Add
    //Product Category Update
    //Product Category Delete
    //Product Category Get All
    //Product Category Get By Id
    //ProductCategory-End

    boolean createProductCategory(String name, String description);
    boolean updateProductCategory(long id, String name, String description);
    boolean deleteProductCategory(long id);
    List<ProductCategory> getAllProductCategory();
    ProductCategory getProductCategoryById(long id);
    

}