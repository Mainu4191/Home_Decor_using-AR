package com.example.arproject1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.arproject1.Entities.UsersEntity;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

    UsersEntity findByEmail(String email);
    
    
}
