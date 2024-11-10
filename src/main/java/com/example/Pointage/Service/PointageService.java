package com.example.Pointage.Service;



import com.example.Pointage.Entity.pointage;
import com.example.Pointage.Repository.PointageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PointageService {

    @Autowired
    private PointageRepository pointageRepository;

    // CRUD Basique
    public pointage createPointage(pointage pointage) {
        return pointageRepository.save(pointage);
    }

    public List<pointage> getAllPointages() {
        return pointageRepository.findAll();
    }

    public Optional<pointage> getPointageById(Long id) {
        return pointageRepository.findById(id);
    }

    public pointage updatePointage(Long id, pointage newPointage) {
        return pointageRepository.findById(id)
                .map(pointage -> {
                    pointage.setNomEmploye(newPointage.getNomEmploye());
                    pointage.setTotalCongeJours(newPointage.getTotalCongeJours());
                    pointage.setPrisCongeJours(newPointage.getPrisCongeJours());
                    pointage.setTotalSortieMin(newPointage.getTotalSortieMin());
                    pointage.setPrisSortieMin(newPointage.getPrisSortieMin());
                    pointage.setCompteurSortie(newPointage.getCompteurSortie());
                    return pointageRepository.save(pointage);
                }).orElseThrow(() -> new RuntimeException("Pointage not found with id " + id));
    }

    public void deletePointage(Long id) {
        pointageRepository.deleteById(id);
    }
    public int calculateRemainingCongeDays(Long id) {
        return pointageRepository.findById(id)
                .map(pointage -> pointage.getTotalCongeJours() - pointage.getPrisCongeJours())
                .orElseThrow(() -> new RuntimeException("Pointage not found with id " + id));
    }

    public int calculateRemainingSortieMinutes(Long id) {
        return pointageRepository.findById(id)
                .map(pointage -> pointage.getTotalSortieMin() - pointage.getPrisSortieMin())
                .orElseThrow(() -> new RuntimeException("Pointage not found with id " + id));
    }

    public boolean isSortieLimitReached(Long id) {
        return pointageRepository.findById(id)
                .map(pointage -> pointage.getCompteurSortie() >= 5) // Exemple avec une limite de 5 sorties
                .orElse(false);
    }

}
