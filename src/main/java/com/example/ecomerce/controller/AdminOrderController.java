package com.example.ecomerce.controller;

import com.example.ecomerce.request.AdminOrderRequest;
import com.example.ecomerce.request.SalesReportResponse;
import com.example.ecomerce.model.Order;
import com.example.ecomerce.repository.OrderRepository;
import com.example.ecomerce.service.SalesReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/orders")
@CrossOrigin(origins = "*")
public class AdminOrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SalesReportService salesReportService;

    @GetMapping("/all")
    public List<AdminOrderRequest> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(o -> new AdminOrderRequest(
                o.getId(),
                o.getName(),
                o.getPhone(),
                o.getAddress(),
                o.getPincode(),
                o.getPaymentMethod(),
                o.getTotalAmount()
        )).collect(Collectors.toList());
    }

    @GetMapping("/sales-report")
    public SalesReportResponse getSalesReport() {
        return salesReportService.generateReport();
    }
}
