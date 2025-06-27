package com.example.ecomerce.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String brand;
    private String category;
    private double price;
    private int stock;
    private double rating;
    private int reviews;
    private boolean featured;
    private String image;

    @ElementCollection
    private List<String> tags;

    @ElementCollection
    private Map<String, String> specifications;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters and setters
    // (Generate using your IDE)
}
