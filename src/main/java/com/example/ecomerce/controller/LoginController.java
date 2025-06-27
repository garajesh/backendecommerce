package com.example.ecomerce.controller;

import com.example.ecomerce.model.Signup;
import com.example.ecomerce.repository.SignupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")  // Add this if calling from React
public class LoginController {

    @Autowired
    private SignupRepository signupRepository;

    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody Signup loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        // Hardcoded admin login
        if ("admin@gmail.com".equalsIgnoreCase(email) && "admin@123".equals(password)) {
            return ResponseEntity.ok(Collections.singletonMap("role", "admin"));
        }

        // Database check for other users
        Optional<Signup> user = signupRepository.findByEmailAndPassword(email, password);
        if (user.isPresent()) {
            return ResponseEntity.ok(Collections.singletonMap("role", "customer"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
