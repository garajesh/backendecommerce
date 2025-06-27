package com.example.ecomerce.controller;

import com.example.ecomerce.model.Product;
import com.example.ecomerce.repository.ProductRepository;
import com.example.ecomerce.request.HomeProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// HomeProductController.java
@RestController
@RequestMapping("/api/home")
@CrossOrigin(origins = "*")
public class HomeProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/featured-products")
    public List<Product> getFeaturedProducts(@RequestBody HomeProductRequest request) {
        return productRepository.findByFeaturedAndCategory(request.isFeatured(), request.getCategory());
    }
}
