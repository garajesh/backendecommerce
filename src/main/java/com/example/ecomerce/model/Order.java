package com.example.ecomerce.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;
    private String address;
    private String pincode;
    private String paymentMethod;
    private double totalAmount;
    private String createdAt; // Store as formatted date string (optional, or use LocalDateTime)
}