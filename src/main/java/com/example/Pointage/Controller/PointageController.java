package com.example.Pointage.Controller;

import com.example.Pointage.Entity.Pointage;
import com.example.Pointage.Service.PointageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/pointage")
public class PointageController {

    @Autowired
    private PointageService pointageService;


    @PostMapping("/ajouter8")
    public ResponseEntity<Pointage> ajouterPointage(@RequestBody Pointage pointage) {
        Pointage nouveauPointage = pointageService.ajouter3Pointage(pointage);
        return ResponseEntity.ok(nouveauPointage);
    }

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

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Endpoint pour calculer les heures travaillées sur une période pour un employé
    //service avancer
    @GetMapping("/employe/{employeId}/totalHeures")
    public ResponseEntity<Duration> getTotalHeuresTravaillées(
            @PathVariable Long employeId,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {

        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        Duration totalHeures = pointageService.getTotalHeuresParPeriode(employeId, start, end);
        return ResponseEntity.ok(totalHeures);
    }

  /*  @GetMapping("/employe/{employeId}/totalHeures")
    public ResponseEntity<Duration> getTotalHeures(
            @PathVariable Long employeId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {

        Duration totalHeures = pointageService.getTotalHeuresParPeriode(employeId, startDate, endDate);
        return ResponseEntity.ok(totalHeures);
    }
*/
}
