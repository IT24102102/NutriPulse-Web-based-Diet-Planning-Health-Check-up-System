package com.healthpath.models;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    private String first_name;
    private String last_name;
    private String password_hash; // matches your DB
    private String role;

    private String profile_image_url;
    private String specialization;
    private String bio;
    private String default_meeting_url;

    private Boolean is_enabled;

    // getters & setters
}
