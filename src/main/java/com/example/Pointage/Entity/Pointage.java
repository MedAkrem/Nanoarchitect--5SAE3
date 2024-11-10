package com.example.Pointage.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pointage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private LocalDateTime dateEntree;


    private LocalDateTime dateSortie;

    private String status;


    private Long employeId;

    // Méthode pour calculer la durée en minutes
    public Duration getDuree() {
        if (dateEntree != null && dateSortie != null) {
            return Duration.between(dateEntree, dateSortie);
        }
        return Duration.ZERO;
    }
}
