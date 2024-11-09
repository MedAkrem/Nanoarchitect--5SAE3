package tn.esprit.salairems.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Salaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long employeeId;
    private Double baseAmount;
    private Double bonus;
    private Double taxPercentage;  // Tax rate as a percentage, e.g., 20 for 20%
    private Double SalaryBeforeTax;
    private Double SalaryAfterTax;

    // Post-construction method to calculate SalaryBeforeTax and SalaryAfterTax
    public void initializeSalary() {
        // Calculate SalaryBeforeTax (baseAmount + bonus)
        this.SalaryBeforeTax = baseAmount + (bonus != null ? bonus : 0.0);

        // Calculate SalaryAfterTax (SalaryBeforeTax - tax)
        this.SalaryAfterTax = this.SalaryBeforeTax - (this.SalaryBeforeTax * (taxPercentage != null ? taxPercentage : 0.0) / 100);
    }

    public Double getTotalSalaryBeforeTax() {
        // this.SalaryBeforeTax=baseAmount + (bonus != null ? bonus : 0.0);
        return SalaryBeforeTax;
    }

    public Double getTotalSalaryAfterTax() {
        //Double totalBeforeTax = getTotalSalaryBeforeTax();
        //this.SalaryAfterTax= totalBeforeTax - (totalBeforeTax * (taxPercentage != null ? taxPercentage : 0.0) / 100);
        return SalaryAfterTax;
    }

    /*
    public void setTotalSalaryBeforeTax(Double totalSalaryBeforeTax) {
        this.SalaryBeforeTax = totalSalaryBeforeTax;
    }
    public void setTotalSalaryAfterTax(Double totalSalaryAfterTax) {
        this.SalaryAfterTax = totalSalaryAfterTax;
    }
     */
}
