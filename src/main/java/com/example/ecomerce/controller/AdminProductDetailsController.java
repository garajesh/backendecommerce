package com.example.ecomerce.controller;

import com.example.ecomerce.model.Product;
import com.example.ecomerce.repository.ProductRepository;
import com.example.ecomerce.request.AdminProductDetailsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/product-details")
@CrossOrigin(origins = "*")
public class AdminProductDetailsController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public List<Product> filterProducts(@RequestBody AdminProductDetailsRequest request) {
        List<Product> products = productRepository.findAll();

        // Filter by search
        if (request.getSearch() != null && !request.getSearch().isEmpty()) {
            String keyword = request.getSearch().toLowerCase();
            products = products.stream()
                    .filter(p -> p.getName().toLowerCase().contains(keyword)
                            || p.getBrand().toLowerCase().contains(keyword))
                    .collect(Collectors.toList());
        }

        // Filter by category
        if (request.getCategory() != null && !request.getCategory().isEmpty()) {
            products = products.stream()
                    .filter(p -> p.getCategory().equalsIgnoreCase(request.getCategory()))
                    .collect(Collectors.toList());
        }

        // Sorting
        if (request.getSortBy() != null) {
            Comparator<Product> comparator;
            switch (request.getSortBy()) {
                case "price":
                    comparator = Comparator.comparing(Product::getPrice);
                    break;
                case "name":
                    comparator = Comparator.comparing(Product::getName);
                    break;
                case "rating":
                    comparator = Comparator.comparing(Product::getRating);
                    break;
                default:
                    comparator = Comparator.comparing(Product::getName);
            }

            if ("desc".equalsIgnoreCase(request.getSortOrder())) {
                comparator = comparator.reversed();
            }

            products = products.stream().sorted(comparator).collect(Collectors.toList());
        }

        return products;
    }
}
