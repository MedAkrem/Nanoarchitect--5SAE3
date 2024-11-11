package tn.esprit.salairems.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto implements Serializable {
    private Long id;
    private String firstname;
    private String lastname;
    private String role;
    private String sexe;


}
