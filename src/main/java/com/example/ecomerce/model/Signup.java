package com.example.ecomerce.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "signups")
public class Signup {




    private String fullName;
    @Id
    private String email;
    private String password;

    // Getters and Setters
}
