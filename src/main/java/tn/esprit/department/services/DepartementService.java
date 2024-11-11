package tn.esprit.department.services;

import tn.esprit.department.DTOs.DepartmentDto;
import java.util.List;

public interface DepartementService {
    DepartmentDto createDepartment(DepartmentDto departmentDto);
    DepartmentDto updateDepartment(Long id, DepartmentDto departmentDto);
    void deleteDepartment(Long id);
    DepartmentDto getDepartmentById(Long id);
    List<DepartmentDto> getAllDepartments();
}
