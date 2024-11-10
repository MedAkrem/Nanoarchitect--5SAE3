package com.esprit.tn.demande.AppResouces.Services.interfaces;




import com.esprit.tn.demande.AppResouces.Models.DTOs.DemandeDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DemandeService {
    DemandeDto createDemande(DemandeDto dto);
    DemandeDto updateDemande(Long id, DemandeDto dto);
    void deleteDemande(Long id);
    DemandeDto getDemandeById(Long id);
    List<DemandeDto> getAllDemande();



}



