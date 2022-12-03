package com.example.example_product_management.repositories;

import com.example.example_product_management.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
