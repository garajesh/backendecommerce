package com.example.ecomerce.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlySalesSummary {
    private String month;
    private int totalOrders;
    private double totalSales;
    private int totalQuantitySold;
    private String topCategory;
    private String topProduct;
    private int totalCustomers;
}