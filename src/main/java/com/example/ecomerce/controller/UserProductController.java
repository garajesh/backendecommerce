package com.example.ecomerce.controller;

import com.example.ecomerce.model.Product;
import com.example.ecomerce.request.UserProductRequest;
import com.example.ecomerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user-products")
@CrossOrigin(origins = "*")
public class UserProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/filter")
    public List<Product> getFilteredProducts(@RequestBody UserProductRequest request) {
        return productRepository.findAll().stream()
                .filter(p -> request.getSearchQuery() == null ||
                        p.getName().toLowerCase().contains(request.getSearchQuery().toLowerCase()) ||
                        p.getDescription().toLowerCase().contains(request.getSearchQuery().toLowerCase()) ||
                        p.getBrand().toLowerCase().contains(request.getSearchQuery().toLowerCase()))
                .filter(p -> request.getFilterCategory() == null || request.getFilterCategory().equalsIgnoreCase("All") ||
                        p.getCategory().equalsIgnoreCase(request.getFilterCategory()))
                .filter(p -> {
                    String priceRange = request.getPriceRange();
                    if (priceRange == null || priceRange.equals("All")) return true;
                    if (priceRange.equals("under1000")) return p.getPrice() < 1000;
                    if (priceRange.equals("1000to2500")) return p.getPrice() >= 1000 && p.getPrice() <= 2500;
                    if (priceRange.equals("above2500")) return p.getPrice() > 2500;
                    return true;
                })
                .filter(p -> request.getBrandFilter() == null || request.getBrandFilter().equals("All") ||
                        p.getBrand().equalsIgnoreCase(request.getBrandFilter()))
                .filter(p -> {
                    String ratingFilter = request.getRatingFilter();
                    if (ratingFilter == null || ratingFilter.equals("All")) return true;
                    try {
                        float rating = Float.parseFloat(ratingFilter);
                        return p.getRating() >= rating;
                    } catch (NumberFormatException e) {
                        return true;
                    }
                })
                .filter(p -> !request.isInStockOnly() || p.getStock() > 0)
                .sorted(getComparator(request.getSortBy()))
                .collect(Collectors.toList());
    }

    private Comparator<Product> getComparator(String sortBy) {
        if (sortBy == null) return Comparator.comparing(Product::getName);
        return switch (sortBy) {
            case "price-low" -> Comparator.comparingDouble(Product::getPrice);
            case "price-high" -> Comparator.comparingDouble(Product::getPrice).reversed();
            case "rating" -> Comparator.comparingDouble(Product::getRating).reversed();
            case "reviews" -> Comparator.comparingInt(Product::getReviews).reversed();
            default -> Comparator.comparing(Product::getName);
        };
    }
}
