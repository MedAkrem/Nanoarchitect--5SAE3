package tn.esprit.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tn.esprit.FeignClient.DepartmentClient;
import tn.esprit.FeignClient.PointageClient;
import tn.esprit.PointageDTO;
import tn.esprit.entities.DepartmentDto;
import tn.esprit.entities.Employee;
import tn.esprit.repository.EmployeeRepository;
import tn.esprit.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {


	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	DepartmentClient departmentClient;
	@Autowired
	 PointageClient pointageClient;

	public void addEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	public void updateEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	public List<Employee> findAllEmployee(String filter,int page,int limit) {
		Pageable pageable = PageRequest.of(page, limit);
		return employeeRepository.findAll(filter,pageable);
	}

	@Override
	public void deleteEmployee(Long id) {
		if (employeeRepository.existsById(id)) {
			employeeRepository.deleteById(id);
		} else {
			throw new RuntimeException("Employé avec ID " + id + " non trouvé");
		}
	}

	public DepartmentDto getEmployeeDepartment(Long departmentId) {
		return departmentClient.getDepartmentById(departmentId);
	}
	@Override
	public Employee findEmployee(Long id) {
		return employeeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Employé avec ID " + id + " non trouvé"));
	}

	public void assignEmployeeToDepartment(Long employeeId, Long departmentId) {
		departmentClient.addEmployeeToDepartment(departmentId, employeeId);
	}

	public List<Long> getDepartmentEmployees(Long departmentId) {
		return departmentClient.getEmployeesInDepartment(departmentId);
	}




	@Autowired
	public EmployeeServiceImpl(PointageClient pointageClient) {
		this.pointageClient = pointageClient;
	}

	public List<PointageDTO> getPointagesForEmploye(Long employeId) {
		return pointageClient.getPointagesByEmployeId(employeId);
	}

}
