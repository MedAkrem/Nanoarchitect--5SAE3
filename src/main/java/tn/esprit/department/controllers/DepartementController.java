package tn.esprit.department.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.department.DTOs.DepartmentDto;
//import tn.esprit.department.services.DemandeClient;
import tn.esprit.department.services.DepartementService;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartementController {

    @Autowired
    private DepartementService departementService;

    /*
    @Autowired
    private DemandeClient demandeClient;

    @RequestMapping("/demandes")
    public ResponseEntity<List<DemandeDto>> getAllDemande() {
        List<DemandeDto> demandes = DemandeClient.getAllDemande(); // Method call fixed
        return new ResponseEntity<>(demandes, HttpStatus.OK);
    }

 */


    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto) {
        return ResponseEntity.ok(departementService.createDepartment(departmentDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable Long id, @RequestBody DepartmentDto departmentDto) {
        return ResponseEntity.ok(departementService.updateDepartment(id, departmentDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departementService.deleteDepartment(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long id) {
        return ResponseEntity.ok(departementService.getDepartmentById(id));
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        return ResponseEntity.ok(departementService.getAllDepartments());
    }
}
