package com.tn.esprit.department.services;



import com.tn.esprit.department.client.UserClient;
import com.tn.esprit.department.dto.UserDTO;
import com.tn.esprit.department.entity.Department;
import com.tn.esprit.department.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private UserClient userClient;

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    public List<UserDTO> getUsersByDepartmentId(Long departmentId) {
        return userClient.getUsersByDepartmentId(departmentId);
    }

    public Department updateDepartment(Long id, Department departmentDetails) {
        Department department = departmentRepository.findById(id).orElseThrow();
        department.setName(departmentDetails.getName());
        department.setType(departmentDetails.getType());
        department.setChefId(departmentDetails.getChefId());
        return departmentRepository.save(department);
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}