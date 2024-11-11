package com.tn.esprit.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    private String firstname;
    private String lastname;
    private String password;
    private String role;
    private String sexe;

    @Column(name = "departement_id")
    private Long departementId;
}