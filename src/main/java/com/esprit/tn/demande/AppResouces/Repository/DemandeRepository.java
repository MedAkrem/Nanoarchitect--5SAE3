package com.esprit.tn.demande.AppResouces.Repository;

import com.esprit.tn.demande.AppResouces.Models.Entities.Demande;

import org.springframework.data.jpa.repository.JpaRepository;






public interface DemandeRepository extends JpaRepository<Demande, Long> {
}
