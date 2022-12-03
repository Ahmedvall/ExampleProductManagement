package com.example.example_product_management.repositories;

import com.example.example_product_management.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
