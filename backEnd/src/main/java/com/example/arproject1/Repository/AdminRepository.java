package com.example.arproject1.Repository;

import org.springframework.stereotype.Repository;

import com.example.arproject1.Entities.AdminEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
    boolean existsByEmail(String email);
    Optional<AdminEntity> findByEmail(String email);
}


