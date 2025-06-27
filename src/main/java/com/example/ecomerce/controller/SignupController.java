package com.example.ecomerce.controller;



import com.example.ecomerce.model.Signup;
import com.example.ecomerce.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/signup")
public class SignupController {

    @Autowired
    private SignupService signupService;

    @PostMapping
    public Signup signup(@RequestBody Signup signup) {
        try {
            return signupService.register(signup);
        } catch (Exception e) {
            e.printStackTrace(); // 👈 this prints real error to console
            throw e;
        }
    }
}
