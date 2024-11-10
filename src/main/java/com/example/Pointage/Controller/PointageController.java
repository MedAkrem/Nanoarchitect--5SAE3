package com.example.Pointage.Controller;



import com.example.Pointage.Entity.pointage;
import com.example.Pointage.Service.PointageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pointages")
public class PointageController {

    @Autowired
    private PointageService pointageService;

    // CRUD Endpoints
    @PostMapping
    public pointage createPointage(@RequestBody pointage pointage) {
        return pointageService.createPointage(pointage);
    }

    @GetMapping
    public List<pointage> getAllPointages() {
        return pointageService.getAllPointages();
    }

    @GetMapping("/{id}")
    public pointage getPointageById(@PathVariable Long id) {
        return pointageService.getPointageById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public pointage updatePointage(@PathVariable Long id, @RequestBody pointage newPointage) {
        return pointageService.updatePointage(id, newPointage);
    }

    @DeleteMapping("/{id}")
    public void deletePointage(@PathVariable Long id) {
        pointageService.deletePointage(id);
    }

    @GetMapping("/{id}/remaining-conge-days")
    public int getRemainingCongeDays(@PathVariable Long id) {
        return pointageService.calculateRemainingCongeDays(id);
    }

    @GetMapping("/{id}/remaining-sortie-minutes")
    public int getRemainingSortieMinutes(@PathVariable Long id) {
        return pointageService.calculateRemainingSortieMinutes(id);
    }

    @GetMapping("/{id}/sortie-limit-reached")
    public boolean isSortieLimitReached(@PathVariable Long id) {
        return pointageService.isSortieLimitReached(id);
    }
/*
    @PostMapping("/{id}/add-conge-days")
    public pointage addCongeDays(@PathVariable Long id, @RequestParam int days) {
        return pointageService.addCongeDays(id, days);
    }

    @PostMapping("/{id}/add-sortie-minutes")
    public pointage addSortieMinutes(@PathVariable Long id, @RequestParam int minutes) {
        return pointageService.addSortieMinutes(id, minutes);
    }*/
}

