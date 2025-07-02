package com.example.ecomerce.service;

import com.example.ecomerce.request.SalesReportResponse;
import com.example.ecomerce.request.MonthlySalesSummary;
import com.example.ecomerce.model.Order;
import com.example.ecomerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SalesReportService {

    @Autowired
    private OrderRepository orderRepo;

    // Monthly report by filter (e.g., /sales-report?month=2025-07)
    public SalesReportResponse generateReport(String month) {
        List<Order> orders = orderRepo.findAll().stream()
                .filter(order -> order.getCreatedAt().startsWith(month)) // format: YYYY-MM
                .collect(Collectors.toList());

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

    // All-time report
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

    // Monthly summarized data for each month
    public List<MonthlySalesSummary> generateMonthlyReport() {
        List<Order> orders = orderRepo.findAll();

        return orders.stream()
                .collect(Collectors.groupingBy(
                        o -> o.getCreatedAt().substring(0, 7),
                        Collectors.toList()
                ))
                .entrySet().stream()
                .map(entry -> {
                    String month = entry.getKey();
                    List<Order> monthlyOrders = entry.getValue();

                    double totalSales = monthlyOrders.stream().mapToDouble(Order::getTotalAmount).sum();
                    int totalOrders = monthlyOrders.size();
                    int totalQuantitySold = monthlyOrders.stream().mapToInt(Order::getQuantity).sum();
                    int totalCustomers = (int) monthlyOrders.stream().map(Order::getCustomerId).distinct().count();

                    String topProduct = monthlyOrders.stream()
                            .collect(Collectors.groupingBy(Order::getProductName, Collectors.counting()))
                            .entrySet().stream()
                            .max(Map.Entry.comparingByValue())
                            .map(Map.Entry::getKey).orElse("N/A");

                    String topCategory = monthlyOrders.stream()
                            .collect(Collectors.groupingBy(Order::getCategory, Collectors.counting()))
                            .entrySet().stream()
                            .max(Map.Entry.comparingByValue())
                            .map(Map.Entry::getKey).orElse("N/A");

                    return new MonthlySalesSummary(month, totalOrders, totalSales, totalQuantitySold, topCategory, topProduct, totalCustomers);
                })
                .sorted(Comparator.comparing(MonthlySalesSummary::getMonth).reversed())
                .collect(Collectors.toList());
    }
}

