package com.example.example_product_management.controllers;

import com.example.example_product_management.entities.Product;
import com.example.example_product_management.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/products")
@RestController
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<Product> products() {
        return productService.findProducts();
    }

    @GetMapping("/{id}")
    public Product productById(@PathVariable("id") Long id) {
        return productService.findProductById(id);
    }

    @PostMapping
    public List<Product> products(@RequestBody List<Product> products) {
        return productService.saveProducts(products);
    }

    @DeleteMapping
    public void delete(@RequestBody List<Long> ids) {
        productService.deleteProducts(ids);
    }

}
