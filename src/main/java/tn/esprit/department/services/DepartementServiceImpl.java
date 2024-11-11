package tn.esprit.department.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.department.DTOs.DepartmentDto;
import tn.esprit.department.Entities.Departement;
import tn.esprit.department.repositories.DepartementRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartementServiceImpl implements DepartementService {

    @Autowired
    private DepartementRepository departementRepository;
/*
    @Autowired
    private DemandeClient DemandeClient;

    public List<DemandeDto> getallDemandes() {
        return DemandeClient.getAllDemande();
    }

 */



    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Departement department = new Departement();
        department.setName(departmentDto.getName());
        department.setType(departmentDto.getType());
        department.setTotalemployee(departmentDto.getTotalemployee());
        department.setChefId(departmentDto.getChefId());
        department = departementRepository.save(department);
        departmentDto.setId(department.getId());
        return departmentDto;
    }

    @Override
    public DepartmentDto updateDepartment(Long id, DepartmentDto departmentDto) {
        Departement department = departementRepository.findById(id).orElseThrow(() -> new RuntimeException("Department not found"));
        department.setName(departmentDto.getName());
        department.setType(departmentDto.getType());
        department.setTotalemployee(departmentDto.getTotalemployee());
        department.setChefId(departmentDto.getChefId());
        departementRepository.save(department);
        return departmentDto;
    }

    @Override
    public void deleteDepartment(Long id) {
        departementRepository.deleteById(id);
    }

    @Override
    public DepartmentDto getDepartmentById(Long id) {
        Departement department = departementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        return new DepartmentDto(department.getId(), department.getName(), department.getType(), department.getTotalemployee(), department.getChefId());
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        return departementRepository.findAll().stream()
                .map(department -> new DepartmentDto(department.getId(), department.getName(), department.getType(), department.getTotalemployee(), department.getChefId()))
                .collect(Collectors.toList());
    }

}
