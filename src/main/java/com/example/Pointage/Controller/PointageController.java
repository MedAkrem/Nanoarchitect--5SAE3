package com.example.Pointage.Controller;

import com.example.Pointage.Entity.Pointage;
import com.example.Pointage.Service.PointageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pointage")
public class PointageController {

    @Autowired
    private PointageService pointageService;

    // Ajouter un pointage pour un employé
   /* @PostMapping("/ajouter")
    public ResponseEntity<Pointage> ajouterPointage(@RequestBody Pointage pointage) {
        Pointage nouveauPointage = pointageService.ajouterPointageemployeId(,pointage);
        return ResponseEntity.ok(nouveauPointage);
    }*/
    @PostMapping("/ajouter/{employeId}")
    public ResponseEntity<Pointage> ajouterPointage(
            @PathVariable Long employeId,
            @RequestBody Pointage pointage) {
        Pointage nouveauPointage = pointageService.ajouterPointage(employeId, pointage);
        return ResponseEntity.ok(nouveauPointage);
    }

    //  les pointages d'un employé  sepcifique thotoliu id
    @GetMapping("/employe/{employeId}")
    public ResponseEntity<List<Pointage>> getAllPointageParEmploye(@PathVariable Long employeId) {
        List<Pointage> pointages = pointageService.getAllPointageParEmploye(employeId);
        return ResponseEntity.ok(pointages);
    }

    //  les pointages mtaa nes elkol
    @GetMapping("/tous")
    public ResponseEntity<List<Pointage>> getAllPointage() {
        List<Pointage> pointages = pointageService.getAllPointage();
        return ResponseEntity.ok(pointages);
    }
}
