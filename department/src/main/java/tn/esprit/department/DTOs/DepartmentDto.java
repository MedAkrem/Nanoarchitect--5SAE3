package tn.esprit.department.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.department.ENUMs.TypeDepartement;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    private Long id;
    private String name;
    private TypeDepartement type;
    private Long chefId;  // Assuming this corresponds to a user or manager's ID
}
