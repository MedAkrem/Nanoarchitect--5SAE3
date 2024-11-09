package tn.esprit.salairems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.salairems.entity.Salaire;
import tn.esprit.salairems.repository.SalaireRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SalaireService {

    @Autowired
    private SalaireRepository repository;

    public Salaire createSalary(Salaire salary) {
        // Ensure the salary object has a null ID to prevent accidental updates
        salary.setId(null);
        // Check if the salary already exists for the same employee
        if (repository.findByEmployeeId(salary.getEmployeeId()) != null) {
            throw new RuntimeException("Salary already exists for employee with ID " + salary.getEmployeeId());
        }
        // Initialize SalaryBeforeTax and SalaryAfterTax
        salary.initializeSalary();
        return repository.save(salary);
    }


    public Salaire updateSalary(Long id, Salaire salary) {
        // Check if the Salary with the given ID exists
        Optional<Salaire> existingSalary = repository.findById(id);

        if (existingSalary.isPresent()) {
            Salaire existing = existingSalary.get();
            // Update fields as needed
            existing.setEmployeeId(salary.getEmployeeId());
            existing.setBaseAmount(salary.getBaseAmount());
            existing.setBonus(salary.getBonus());
            existing.setTaxPercentage(salary.getTaxPercentage());
            // Recalculate SalaryBeforeTax and SalaryAfterTax
            existing.initializeSalary();


            return repository.save(existing);
        } else {
            throw new RuntimeException("Salary with ID " + id + " not found");
        }
    }


    public Salaire getSalaryByEmployeeId(Long employeeId) {
        return repository.findByEmployeeId(employeeId);
    }

    public Double getSalaryBeforeTax(Long employeeId) {
        Salaire salary = getSalaryByEmployeeId(employeeId);
        return salary != null ? salary.getTotalSalaryBeforeTax() : null;
    }

    public Double getSalaryAfterTax(Long employeeId) {
        Salaire salary = getSalaryByEmployeeId(employeeId);
        return salary != null ? salary.getTotalSalaryAfterTax() : null;
    }

    public List<Salaire> getAllSalaries() {
        return repository.findAll();
    }

    public void deleteSalaryById(Long id) {
        repository.deleteById(id);
    }

    /**
     * Calculate the total sum of all salaries before taxes.
     * returns The total sum of salaries before taxes.
     **/
    public Double calculateTotalSalaryBeforeTax() {
        List<Salaire> salaries = repository.findAll();
        return salaries.stream()
                .mapToDouble(Salaire::getTotalSalaryBeforeTax)
                .sum();
    }

    /**
     * Calculate the total sum of all salaries after taxes.
     * returns The total sum of salaries after taxes.
     **/
    public Double calculateTotalSalaryAfterTax() {
        List<Salaire> salaries = repository.findAll();
        return salaries.stream()
                .mapToDouble(Salaire::getTotalSalaryAfterTax)
                .sum();
    }

    /**
     * Calculate the average of all salaries before taxes.
     * return The average of salaries before taxes, or 0.0 if no salaries are present.
     **/
    public Double calculateAverageSalaryBeforeTax() {
        List<Salaire> salaries = repository.findAll();
        return salaries.isEmpty() ? 0.0 : salaries.stream()
                .mapToDouble(Salaire::getTotalSalaryBeforeTax)
                .average()
                .orElse(0.0);
    }

    /**
     * Calculate the average of all salaries after taxes.
     * returns The average of salaries after taxes, or 0.0 if no salaries are present.
     **/
    public Double calculateAverageSalaryAfterTax() {
        List<Salaire> salaries = repository.findAll();
        return salaries.isEmpty() ? 0.0 : salaries.stream()
                .mapToDouble(Salaire::getTotalSalaryAfterTax)
                .average()
                .orElse(0.0);
    }








}
