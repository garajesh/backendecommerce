package com.example.ecomerce.controller;

import com.example.ecomerce.model.Contact;
import com.example.ecomerce.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public Contact submitFeedback(@RequestBody Contact contact) {
        return feedbackService.saveFeedback(contact);
    }

    @GetMapping
    public List<Contact> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }

    @DeleteMapping("/{id}")
    public void deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
    }
}
