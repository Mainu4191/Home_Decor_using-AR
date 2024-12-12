package com.example.arproject1.Services;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.arproject1.Entities.Products;
import com.example.arproject1.Repository.ProductsRepository;

@Service
public class ProductsServicesImpl implements ProductsServices {

    private final ProductsRepository productsRepository;

    public ProductsServicesImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public boolean createProduct(String name, String description, String image, String productCategoryID, String model,
            String modelBufferFile, String folderName, String price) {

        Products product = new Products();
        product.setName(name);
        product.setDescription(description);
        product.setImage(image);
        product.setproductCategoryID(productCategoryID);
        product.setModel(model);
        product.setModelBufferFile(modelBufferFile);
        product.setFolderName(folderName);
        product.setPrice(price);
        Date date = new Date(System.currentTimeMillis());
        product.setCreatedAt(date.toString());
        product.setUpdatedAt(date.toString());

        productsRepository.save(product);

        return true;

    }

    @Override
    public boolean updateProduct(long id, String name, String description, String image, String productCategoryID,
            String model, String price) {

        Products product = productsRepository.findById(id).get();

        product.setName(name);
        product.setDescription(description);
        product.setImage(image);
        product.setproductCategoryID(productCategoryID);
        product.setModel(model);
        product.setPrice(price);

        productsRepository.save(product);

        return true;
    }

    @SuppressWarnings("null")
    @Override
    public boolean deleteProduct(long id) {

        Products product = productsRepository.findById(id).get();

        productsRepository.delete(product);

        return true;
    }

    @Override
    public List<Products> getAllProduct() {

        List<Products> products = productsRepository.findAll();

        return products;

    }

    @Override
    public Products getProductById(long id) {

        Products product = productsRepository.findById(id).get();

        return product;
    }

}
