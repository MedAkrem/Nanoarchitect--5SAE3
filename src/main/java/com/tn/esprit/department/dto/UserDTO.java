package com.tn.esprit.department.dto;

import jakarta.persistence.Column;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String firstname;
    private String lastname;
    private String role;
    @Column(name = "departement_id")
    private Long departementId;
}