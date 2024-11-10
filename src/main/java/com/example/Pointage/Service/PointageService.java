package com.example.Pointage.Service;

import com.example.Pointage.FeignClient.EmployeClient;
import com.example.Pointage.Entity.Pointage;
import com.example.Pointage.Repository.PointageRepository;
import com.example.Pointage.DTO.EmployeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointageService {

    @Autowired
    private PointageRepository pointageRepository;

    @Autowired
    private EmployeClient employeClient;

    public Pointage ajouter3Pointage(Pointage pointage) {
        return pointageRepository.save(pointage);
    }
    public Pointage ajouterPointage(Long employeId, Pointage pointage) {
        // Vérifie que l'employé existe en appelant le microservice Employe
        EmployeDTO employe = employeClient.getEmployeById(employeId);
        if (employe != null) {
            pointage.setEmployeId(employeId); // Associe l'employé par son ID
            return pointageRepository.save(pointage);
        } else {
            throw new RuntimeException("Employé avec ID " + employeId + " non trouvé");
        }
    }

    public List<Pointage> getAllPointageParEmploye(Long employeId) {
        EmployeDTO employe = employeClient.getEmployeById(employeId);
        System.out.println("Informations de l'employé : " + employe.getNom());
        return pointageRepository.findByEmployeId(employeId);
    }

    public List<Pointage> getAllPointage() {
        return pointageRepository.findAll();
    }



    @Autowired
    public PointageService(EmployeClient employeClient) {
        this.employeClient = employeClient;
    }

    public EmployeDTO obtenirEmploye(Long employeId) {
        return employeClient.getEmployeById(employeId);
    }
}
