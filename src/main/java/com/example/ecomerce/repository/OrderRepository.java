package com.example.ecomerce.repository;

import com.example.ecomerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // No custom methods needed now
}