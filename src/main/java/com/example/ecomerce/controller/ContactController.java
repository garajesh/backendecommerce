package com.example.ecomerce.controller;

import com.example.ecomerce.model.Contact;
import com.example.ecomerce.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    public Contact submitContact(@RequestBody Contact contact) {
        return contactService.saveContact(contact);
    }
}
