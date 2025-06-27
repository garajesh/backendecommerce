package com.example.ecomerce.request;

import lombok.Data;

@Data
public class AdminProductDetailsRequest {
    private String search;      // Name or brand
    private String category;
    private String sortBy;      // price, name, rating
    private String sortOrder;   // asc or desc
}
