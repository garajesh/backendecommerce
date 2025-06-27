package com.example.ecomerce.service;

import com.example.ecomerce.model.Product;
import com.example.ecomerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Optional<Product> updateProduct(Long id, Product updatedProduct) {
        return productRepository.findById(id).map(product -> {
            updatedProduct.setId(product.getId());
            return productRepository.save(updatedProduct);
        });
    }
}
