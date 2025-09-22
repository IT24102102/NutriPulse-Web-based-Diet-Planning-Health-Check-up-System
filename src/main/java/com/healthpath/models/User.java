package com.healthpath.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;  // To be hashed later
    private String role;  // PATIENT, NUTRITIONIST, PHYSICIAN
    private double weight;
    private double height;
    private String healthNotes;  // e.g., diabetes, nut allergy
}
