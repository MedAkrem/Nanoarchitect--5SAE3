package com.esprit.tn.demande.AppResouces.Models.DTOs;

import com.esprit.tn.demande.AppResouces.Models.ENUMs.CategorieConge;
import com.esprit.tn.demande.AppResouces.Models.ENUMs.StatusDemande;
import com.esprit.tn.demande.AppResouces.Models.ENUMs.TypeDemande;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DemandeDto {

    private Long id;

    private Long userId;

    private TypeDemande type;
    @Enumerated(EnumType.STRING)
    private CategorieConge categorie;
    private String motif;
    private LocalDateTime debut;
    private LocalDateTime fin;

    private List<String> attachments;
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

