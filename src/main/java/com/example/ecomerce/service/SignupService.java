package com.example.ecomerce.service;



import com.example.ecomerce.model.Signup;
import com.example.ecomerce.repository.SignupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignupService {
    @Autowired
    private SignupRepository signupRepository;

    public Signup register(Signup signup) {
        if (signupRepository.existsByEmail(signup.getEmail())) {
            throw new RuntimeException("Email already registered");
        }
        return signupRepository.save(signup);
    }
}
