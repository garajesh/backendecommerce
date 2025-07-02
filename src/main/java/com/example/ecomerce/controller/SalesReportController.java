package com.example.ecomerce.controller;

import com.example.ecomerce.request.SalesReportResponse;
import com.example.ecomerce.request.MonthlySalesSummary;
import com.example.ecomerce.service.SalesReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class SalesReportController {

    @Autowired
    private SalesReportService salesReportService;

    @GetMapping("/sales-report")
    public SalesReportResponse getSalesReport(@RequestParam(required = false) String month) {
        if (month != null && !month.isEmpty()) {
            return salesReportService.generateReport(month);
        }
        return salesReportService.generateReport();
    }

    @GetMapping("/orders/monthly-report")
    public List<MonthlySalesSummary> getMonthlyReport() {
        return salesReportService.generateMonthlyReport();
    }
}
