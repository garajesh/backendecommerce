package com.example.ecomerce.request;

import java.util.List;

public class UserProductRequest {
    private String searchQuery;
    private String filterCategory;
    private String priceRange;
    private String brandFilter;
    private String ratingFilter;
    private String sortBy;
    private boolean inStockOnly;
    private List<String> selectedColors;

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public String getFilterCategory() {
        return filterCategory;
    }

    public void setFilterCategory(String filterCategory) {
        this.filterCategory = filterCategory;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public String getBrandFilter() {
        return brandFilter;
    }

    public void setBrandFilter(String brandFilter) {
        this.brandFilter = brandFilter;
    }

    public String getRatingFilter() {
        return ratingFilter;
    }

    public void setRatingFilter(String ratingFilter) {
        this.ratingFilter = ratingFilter;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public boolean isInStockOnly() {
        return inStockOnly;
    }

    public void setInStockOnly(boolean inStockOnly) {
        this.inStockOnly = inStockOnly;
    }

    public List<String> getSelectedColors() {
        return selectedColors;
    }

    public void setSelectedColors(List<String> selectedColors) {
        this.selectedColors = selectedColors;
    }
}
