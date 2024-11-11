package com.example.Pointage.FeignClient;

import com.example.Pointage.DTO.EmployeDTO;
import com.example.Pointage.config.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "employee-service",configuration = FeignClientConfiguration.class)
public interface EmployeClient {
    @GetMapping("/api/employee/{id}")
    EmployeDTO getEmployeById(@PathVariable("id") Long id);




}
