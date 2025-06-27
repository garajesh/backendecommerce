package com.example.ecomerce.service;

import com.example.ecomerce.model.Contact;
import com.example.ecomerce.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private ContactRepository contactRepository;

    public Contact saveFeedback(Contact contact) {
        return contactRepository.save(contact);
    }

    public List<Contact> getAllFeedbacks() {
        return contactRepository.findAll();
    }

    public void deleteFeedback(Long id) {
        contactRepository.deleteById(id);
    }
}
