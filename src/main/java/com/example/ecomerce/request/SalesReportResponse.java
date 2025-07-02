package com.example.ecomerce.request;

import lombok.*;
import java.util.*;

@Data
public class SalesReportResponse {
    private double totalSales;
    private int totalOrders;
    private List<OrderSummary> recentOrders;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderSummary {
        private Long id;
        private String customerName;
        private double total;
        private String date;
    }
}
