package com.example.Pointage.FeignClient;

import com.example.Pointage.DTO.EmployeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "employe-service", url = "http://localhost:8081/api/employes") // Assurez-vous que le nom et l'URL correspondent
public interface EmployeClient {

    @GetMapping("/{id}")
    EmployeDTO getEmployeById(@PathVariable("id") Long id);
}
