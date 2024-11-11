package com.esprit.tn.demande.AppRestControllers;

import com.esprit.tn.demande.AppResouces.Models.DTOs.DemandeDto;
import com.esprit.tn.demande.AppResouces.Services.interfaces.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/demandes")
public class DemandeController {

    @Autowired
    private DemandeService demandeService;

    @PostMapping
    public ResponseEntity<DemandeDto> createDemande(@RequestBody DemandeDto demandeDto) {
        DemandeDto createdDemande = demandeService.createDemande(demandeDto);
        return new ResponseEntity<>(createdDemande, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DemandeDto> updateDemande(@PathVariable Long id, @RequestBody DemandeDto demandeDto) {
        DemandeDto updatedDemande = demandeService.updateDemande(id, demandeDto);
        return new ResponseEntity<>(updatedDemande, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDemande(@PathVariable Long id) {
        demandeService.deleteDemande(id);
        return new ResponseEntity<>("Demande deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DemandeDto> getDemandeById(@PathVariable Long id) {
        DemandeDto demandeDto = demandeService.getDemandeById(id);
        return new ResponseEntity<>(demandeDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DemandeDto>> getAllDemande() {
        List<DemandeDto> demandes = demandeService.getAllDemande();
        return new ResponseEntity<>(demandes, HttpStatus.OK);
    }

}
