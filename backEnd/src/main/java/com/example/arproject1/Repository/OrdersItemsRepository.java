package com.example.arproject1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.arproject1.Entities.OrderItems;

@Repository
public interface OrdersItemsRepository extends JpaRepository<OrderItems, Long> {
    
}
