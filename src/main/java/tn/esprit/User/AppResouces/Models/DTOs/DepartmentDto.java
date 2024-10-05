package tn.esprit.User.AppResouces.Models.DTOs;

import tn.esprit.User.AppResouces.Models.ENUMs.TypeDepartement;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    private Long id;
    private String name;
    private TypeDepartement type;
    private Long chefId;
}
