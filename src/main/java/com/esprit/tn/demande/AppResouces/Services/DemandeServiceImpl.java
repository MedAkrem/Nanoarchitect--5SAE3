package com.esprit.tn.demande.AppResouces.Services;

import com.esprit.tn.demande.AppResouces.Models.DTOs.DemandeDto;
import com.esprit.tn.demande.AppResouces.Models.ENUMs.StatusDemande;
import com.esprit.tn.demande.AppResouces.Models.Entities.Demande;
import com.esprit.tn.demande.AppResouces.Repository.DemandeRepository;
import com.esprit.tn.demande.AppResouces.Services.interfaces.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class DemandeServiceImpl implements DemandeService {

    @Autowired
    private DemandeRepository demandeRepository;

    @Override
    public DemandeDto createDemande(DemandeDto dto) {
        Demande demande = new Demande();
        demande.setCategorie(dto.getCategorie());
        demande.setType(dto.getType());
        demande.setMotif(dto.getMotif());
        demande.setDebut(dto.getDebut());
        demande.setFin(dto.getFin());

        // Default initialization for new demandes
        demande.setStatusCd(StatusDemande.PENDING);
        demande.setStatusRrh(StatusDemande.PENDING);
        demande.setStatusDg(StatusDemande.PENDING);
        demande.setDateCreation(LocalDateTime.now());
        demande.setCanceled(false);

        // Save and update DTO with ID and additional fields
        demande = demandeRepository.save(demande);
        dto.setId(demande.getId());
        dto.setDateCreation(demande.getDateCreation());
        dto.setStatusCd(demande.getStatusCd());
        dto.setStatusRrh(demande.getStatusRrh());
        dto.setStatusDg(demande.getStatusDg());
        dto.setCanceled(demande.isCanceled());

        return dto;
    }

    @Override
    public DemandeDto updateDemande(Long id, DemandeDto dto) {
        Demande demande = demandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Demande not found with id: " + id));

        // Update fields
        demande.setCategorie(dto.getCategorie());
        demande.setType(dto.getType());
        demande.setMotif(dto.getMotif());
        demande.setDebut(dto.getDebut());
        demande.setFin(dto.getFin());
        demande.setStatusCd(dto.getStatusCd());
        demande.setStatusRrh(dto.getStatusRrh());
        demande.setStatusDg(dto.getStatusDg());
        demande.setCanceled(dto.isCanceled());

        // Save updated demande
        demande = demandeRepository.save(demande);

        // Update the response DTO
        dto.setId(demande.getId());
        dto.setDateCreation(demande.getDateCreation());
        dto.setStatusCd(demande.getStatusCd());
        dto.setStatusRrh(demande.getStatusRrh());
        dto.setStatusDg(demande.getStatusDg());
        dto.setCanceled(demande.isCanceled());

        return dto;
    }


    @Override
    public void deleteDemande(Long id) {
        if (!demandeRepository.existsById(id)) {
            throw new RuntimeException("Demande not found with id: " + id);
        }
        demandeRepository.deleteById(id);
    }

    @Override
    public DemandeDto getDemandeById(Long id) {
        Demande demande = demandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Demande not found with id: " + id));

        // Convert Demande entity to DemandeDto
        DemandeDto dto = new DemandeDto();
        dto.setId(demande.getId());
        dto.setCategorie(demande.getCategorie());
        dto.setType(demande.getType());
        dto.setMotif(demande.getMotif());
        dto.setDebut(demande.getDebut());
        dto.setFin(demande.getFin());
        dto.setDateCreation(demande.getDateCreation());
        dto.setStatusCd(demande.getStatusCd());
        dto.setStatusRrh(demande.getStatusRrh());
        dto.setStatusDg(demande.getStatusDg());
        dto.setCanceled(demande.isCanceled());

        return dto;
    }

    @Override
    public List<DemandeDto> getAllDemande() {
        return demandeRepository.findAll().stream()
                .map(demande -> {
                    DemandeDto dto = new DemandeDto();
                    dto.setId(demande.getId());
                    dto.setCategorie(demande.getCategorie());
                    dto.setType(demande.getType());
                    dto.setMotif(demande.getMotif());
                    dto.setDebut(demande.getDebut());
                    dto.setFin(demande.getFin());
                    dto.setDateCreation(demande.getDateCreation());
                    dto.setStatusCd(demande.getStatusCd());
                    dto.setStatusRrh(demande.getStatusRrh());
                    dto.setStatusDg(demande.getStatusDg());
                    dto.setCanceled(demande.isCanceled());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
