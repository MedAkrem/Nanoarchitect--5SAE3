package com.tn.esprit.department.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

    @Column(name = "chef_id")
    private Long chefId;
}