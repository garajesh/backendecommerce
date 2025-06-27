package com.example.ecomerce.controller;

import com.example.ecomerce.request.SalesReportResponse;
import com.example.ecomerce.service.SalesReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000") // Adjust if your frontend runs elsewhere
public class SalesReportController {

    @Autowired
    private SalesReportService salesReportService;

    @GetMapping("/sales-report")
    public SalesReportResponse getSalesReport() {
        return salesReportService.generateReport();
    }
}
