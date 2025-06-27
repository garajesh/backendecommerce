package com.example.ecomerce.service;

import com.example.ecomerce.request.SalesReportResponse;
import com.example.ecomerce.model.Order;
import com.example.ecomerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalesReportService {

    @Autowired
    private OrderRepository orderRepo;

    public SalesReportResponse generateReport() {
        List<Order> orders = orderRepo.findAll();

        double totalSales = orders.stream().mapToDouble(Order::getTotalAmount).sum();
        int totalOrders = orders.size();

        List<SalesReportResponse.OrderSummary> recentOrders = orders.stream()
                .sorted(Comparator.comparing(Order::getId).reversed())
                .limit(5)
                .map(order -> new SalesReportResponse.OrderSummary(
                        order.getId(),
                        order.getName(),
                        order.getTotalAmount(),
                        order.getCreatedAt()
                ))
                .collect(Collectors.toList());

        SalesReportResponse response = new SalesReportResponse();
        response.setTotalSales(totalSales);
        response.setTotalOrders(totalOrders);
        response.setRecentOrders(recentOrders);

        return response;
    }
}