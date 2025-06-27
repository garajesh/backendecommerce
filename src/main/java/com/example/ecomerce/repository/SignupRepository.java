package com.example.ecomerce.repository;

import com.example.ecomerce.model.Signup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SignupRepository extends JpaRepository<Signup, Long> {

    // For signup email uniqueness check
    boolean existsByEmail(String email);

    // For login validation
    Optional<Signup> findByEmailAndPassword(String email, String password);
}
