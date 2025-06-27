package com.example.ecomerce.controller;

import com.example.ecomerce.model.Order;
import com.example.ecomerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/submit")
    public Order submitOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }
}
