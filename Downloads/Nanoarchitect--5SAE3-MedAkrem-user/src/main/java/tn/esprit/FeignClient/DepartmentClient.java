package tn.esprit.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import tn.esprit.entities.DepartmentDto;

import java.util.List;


@FeignClient(name = "department-service", url = "http://localhost:8083")
public interface DepartmentClient {
    @GetMapping("/api/departments/{id}")
    DepartmentDto getDepartmentById(@PathVariable("id") Long id);

    @GetMapping("/api/departments")
    List<DepartmentDto> getAllDepartments();

    @PostMapping("/api/departments/{departmentId}/employees/{employeeId}")
    void addEmployeeToDepartment(@PathVariable("departmentId") Long departmentId, @PathVariable("employeeId") Long employeeId);

    @GetMapping("/api/departments/{departmentId}/employees")
    List<Long> getEmployeesInDepartment(@PathVariable("departmentId") Long departmentId);
}
