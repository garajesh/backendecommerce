package com.example.ecomerce.repository;

import com.example.ecomerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findTop6ByFeaturedTrueOrderByCreatedAtDesc();


}
