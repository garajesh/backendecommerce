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
    private String createdAt;

    // ✅ Add the missing fields below
    private int quantity;
    private String productName;
    private String category;
    private Long customerId;
}
