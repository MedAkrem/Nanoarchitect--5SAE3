package tn.esprit.User.AppResouces.Services;

import tn.esprit.User.AppResouces.Models.DTOs.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "department-service", url = "http://localhost:8083")
public interface DepartmentClient {

    @GetMapping("/departments/{id}")
    DepartmentDto getDepartmentById(@PathVariable("id") Long id);

    @GetMapping("/departments")
    List<DepartmentDto> getAllDepartments();
}
