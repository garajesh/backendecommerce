package com.example.ecomerce.request;

// HomeProductRequest.java
public class HomeProductRequest {
    private boolean featured;
    private String category;

    // Getters and Setters
    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

