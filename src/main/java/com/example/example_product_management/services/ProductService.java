package com.example.example_product_management.services;

import com.example.example_product_management.entities.Product;
import com.example.example_product_management.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findProducts() {
        // in real life scenario we should use pagination...
        return productRepository.findAll();
    }

    public Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("<ProductService.findProductById>: product id [ " + id + " ] not found"));
    }

    public List<Product> saveProducts(List<Product> products) {
        return productRepository.saveAll(products);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        Product productToUpdate = productRepository.findById(product.getId())
                .orElseThrow(() -> new RuntimeException("Product with id: [ "+ product.getId() + " ] not found!"));

        return saveProduct(product);
    }

    public void deleteProducts(List<Long> productIds) {
        productRepository.deleteAllById(productIds);
    }
}
