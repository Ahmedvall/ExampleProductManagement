package com.example.example_product_management.services;


import com.example.example_product_management.entities.Product;
import com.example.example_product_management.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class ProductServiceTest {

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Test
    public void noProductReturned() {

        // Assumptions
        given(productRepository.findAll()).willReturn(Collections.emptyList());

        var products = productService.findProducts();

        assertTrue(products.isEmpty());

    }


    @Test
    public void productReturned() {

        List<Product> ps = List.of(
                new Product(1L, "HP", LocalDateTime.now(), LocalDateTime.now()),
                new Product(2L, "ASUS", LocalDateTime.now(), LocalDateTime.now()),
                new Product(3L, "DELL", LocalDateTime.now(), LocalDateTime.now())
        );
        // Assumptions
        given(productRepository.findAll()).willReturn(ps);

        var products = productService.findProducts();

        assertEquals(products, ps);

    }
}
