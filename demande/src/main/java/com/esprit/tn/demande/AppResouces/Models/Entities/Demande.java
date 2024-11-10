package com.esprit.tn.demande.AppResouces.Models.Entities;

import com.esprit.tn.demande.AppResouces.Models.ENUMs.CategorieConge;
import com.esprit.tn.demande.AppResouces.Models.ENUMs.StatusDemande;
import com.esprit.tn.demande.AppResouces.Models.ENUMs.TypeDemande;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "demande")
public class Demande implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeDemande type;

    @Enumerated(EnumType.STRING)
    private CategorieConge categorie;

    private String motif;
    private LocalDateTime debut;
    private LocalDateTime fin;

    @Enumerated(EnumType.STRING)
    private StatusDemande statusCd;

    @Enumerated(EnumType.STRING)
    private StatusDemande statusRrh;

    @Enumerated(EnumType.STRING)
    private StatusDemande statusDg;

    private LocalDateTime dateCreation;
    private LocalDateTime dateReponse;
    private String noteResponse;
    private boolean canceled;
}
