package tn.esprit.salairems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.salairems.entity.Salaire;
import tn.esprit.salairems.service.SalaireService;

import java.util.List;

@RestController
@RequestMapping("/salaires")
public class SalaireController {

    @Autowired
    private SalaireService service;

    // Endpoint to create a new Salary
    @PostMapping("/add-salary")
    public Salaire createSalary(@RequestBody Salaire salary) {
        return service.createSalary(salary);
    }

    // Endpoint to update an existing Salary
    @PutMapping("/update-salary/{id}")
    public Salaire updateSalary(@PathVariable Long id, @RequestBody Salaire salary) {
        return service.updateSalary(id, salary);
    }

    @GetMapping("/employee/{employeeId}")
    public Salaire getSalaryByEmployeeId(@PathVariable Long employeeId) {
        return service.getSalaryByEmployeeId(employeeId);
    }

    @GetMapping("/employee/{employeeId}/before-tax")
    public Double getSalaryBeforeTax(@PathVariable Long employeeId) {
        return service.getSalaryBeforeTax(employeeId);
    }

    @GetMapping("/employee/{employeeId}/after-tax")
    public Double getSalaryAfterTax(@PathVariable Long employeeId) {
        return service.getSalaryAfterTax(employeeId);
    }

    @GetMapping("get-all-salaries")
    public List<Salaire> getAllSalaries() {
        return service.getAllSalaries();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSalaryById(@PathVariable Long id) {
        service.deleteSalaryById(id);
        return "Salary with ID " + id + " has been deleted.";
    }

    @GetMapping("/total-before-tax")
    public Double getTotalSalaryBeforeTax() {
        return service.calculateTotalSalaryBeforeTax();
    }

    @GetMapping("/total-after-tax")
    public Double getTotalSalaryAfterTax() {
        return service.calculateTotalSalaryAfterTax();
    }

    @GetMapping("/average-before-tax")
    public Double getAverageSalaryBeforeTax() {
        return service.calculateAverageSalaryBeforeTax();
    }

    @GetMapping("/average-after-tax")
    public Double getAverageSalaryAfterTax() {
        return service.calculateAverageSalaryAfterTax();
    }


}
