package tn.esprit.User.AppResouces.Models.DTOs;

import tn.esprit.User.AppResouces.Models.ENUMs.Sexe;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {
    private Long id;
    private String nomComplet;
    private String telephone;
}