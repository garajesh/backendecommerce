package com.example.ecomerce.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminOrderRequest {
    private Long id;
    private String name;
    private String phone;
    private String address;
    private String pincode;
    private String paymentMethod;
    private double totalAmount;
}
