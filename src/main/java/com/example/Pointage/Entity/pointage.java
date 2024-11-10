package com.example.Pointage.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class pointage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  /*  @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private User user;*/
    private String NomEmploye;

    private int totalCongeJours;
    private int prisCongeJours;
    private int totalSortieMin;
    private int prisSortieMin;
    private int compteurSortie;

}

