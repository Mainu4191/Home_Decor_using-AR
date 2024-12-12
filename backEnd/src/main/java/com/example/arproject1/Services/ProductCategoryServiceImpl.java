package com.example.arproject1.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.arproject1.Entities.ProductCategory;
import com.example.arproject1.Repository.ProductCategoryRepository;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public boolean createProductCategory(String name, String description) {

        ProductCategory productCategory = new ProductCategory();
        productCategory.setName(name);
        productCategory.setDescription(description);

        productCategoryRepository.save(productCategory);

        return true;

    }

    @Override
    public boolean updateProductCategory(long id, String name, String description) {

        ProductCategory productCategory = productCategoryRepository.findById(id).get();
        productCategory.setName(name);
        productCategory.setDescription(description);

        productCategoryRepository.save(productCategory);

        return true;
    }

    @SuppressWarnings("null")
    @Override
    public boolean deleteProductCategory(long id) {

        ProductCategory productCategory = productCategoryRepository.findById(id).get();
        productCategoryRepository.delete(productCategory);

        return true;
    }

    @Override
    public List<ProductCategory> getAllProductCategory() {

        return productCategoryRepository.findAll();
    }

    @Override
    public ProductCategory getProductCategoryById(long id) {

        return productCategoryRepository.findById(id).get();
    }

}
