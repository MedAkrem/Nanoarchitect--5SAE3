package tn.esprit.controllers;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.esprit.entities.DepartmentDto;
import tn.esprit.entities.Employee;
import tn.esprit.repository.EmployeeRepository;
import tn.esprit.serviceImpl.EmployeeServiceImpl;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	@Autowired
	EmployeeServiceImpl EmployeeService;
    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;
	@Autowired
	EmployeeRepository empRepo;

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@PostMapping("ajouter")
	public ResponseEntity<Employee> ajouterEmployee(@RequestBody Employee employee) {
		Employee savedEmployee = empRepo.save(employee);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
	}


	@DeleteMapping(value="/deleteEmployee/{id}")
	public void supprimerEmployee(@PathVariable Long id) {
		EmployeeService.deleteEmployee(id);
	}
	@GetMapping("/findEmployees")
	public List<Employee> afficherEmployee(@RequestParam(value ="filter",required = false)String filter,@RequestParam(value="page")int page,@RequestParam(value="limit")int limit) {
		if (StringUtils.isBlank(filter))
			filter="";
		return EmployeeService.findAllEmployee(filter,page,limit);
	}
	@GetMapping("/findEmployeeById/{id}")
	public Employee findEmployee(@PathVariable Long id) {
		return EmployeeService.findEmployee(id);
	}
	
	@PutMapping("/updateEmployee")
    public void updateEmployee(@RequestBody Employee employee){
//		Employee employee=new Employee();
//		employee.setId(Employee.getId());
//		employee.setPrenom(Employee.getNom());
//		employee.setDateNaissance(Employee.getDateNaissance());
//		employee.setNom(Employee.getNom());
//		employee.setEmail(Employee.getEmail());
		
		EmployeeService.addEmployee(employee);
	}



	@GetMapping("/{id}")
	public Employee getEmployeById(@PathVariable Long id) {
		// Cherche l'employé par son ID dans le repository
		Optional<Employee> employe = empRepo.findById(id);

		// Retourne l'employé s'il est trouvé, sinon retourne null ou lance une exception
		if (employe.isPresent()) {
			return employe.get();
		} else {
			throw new RuntimeException("Employé avec ID " + id + " non trouvé");
		}
	}

	@GetMapping("/employee/{id}/department")
	public DepartmentDto getEmployeeDepartment(@PathVariable Long id) {
		// Assume employeeService has a method to find an employee's department by ID
		return employeeServiceImpl.getEmployeeDepartment(id);
	}

	@PostMapping("/employee/{employeeId}/assign-to-department/{departmentId}")
	public ResponseEntity<String> assignEmployeeToDepartment(@PathVariable Long employeeId, @PathVariable Long departmentId) {
		employeeServiceImpl.assignEmployeeToDepartment(employeeId, departmentId);
		return ResponseEntity.ok("Employee assigned to department successfully");
	}

	@GetMapping("/department/{departmentId}/employees")
	public List<Long> getEmployeesInDepartment(@PathVariable Long departmentId) {
		return employeeServiceImpl.getDepartmentEmployees(departmentId);
	}
}
