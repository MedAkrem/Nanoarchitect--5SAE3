package com.example.Pointage.FeignClient;

import com.example.Pointage.DTO.EmployeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "employee-service") // Assurez-vous que le nom et l'URL correspondent
public interface EmployeClient {
    @GetMapping("/api/employee/{id}")
    EmployeDTO getEmployeById(@PathVariable("id") Long id);




}
