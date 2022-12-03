package com.example.example_product_management.controllers;

import com.example.example_product_management.entities.Product;
import com.example.example_product_management.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductRestTest {

    @MockBean
    private ProductService productService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getProductById() throws Exception {

        Product product = new Product(22L, "HP", LocalDateTime.now(), LocalDateTime.now());

        given(productService.findProductById(22L)).willReturn(product);

        mockMvc.perform(get("/products/{id}", 22L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(22L))
                .andExpect(jsonPath("$.name").value("HP"));
    }
}
