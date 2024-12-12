package com.example.arproject1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.arproject1.Entities.Products;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {

}