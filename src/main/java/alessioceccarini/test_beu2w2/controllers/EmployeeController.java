package alessioceccarini.test_beu2w2.controllers;


import alessioceccarini.test_beu2w2.entities.Employee;
import alessioceccarini.test_beu2w2.payloads.EmployeeDTO;
import alessioceccarini.test_beu2w2.services.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("employees")
public class EmployeeController {

	private final EmployeesService employeesService;

	@Autowired
	public EmployeeController(EmployeesService employeesService) {
		this.employeesService = employeesService;
	}

	//------------------------------------ G E T ----------------------------------------------
	@GetMapping
	public Page<Employee> getEmployee(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "surname") String orderBY) {
		return employeesService.findAllEmployees(page, size, orderBY);
	}

	//------------------------------------ P O S T  ----------------------------------------------

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Employee addEmployee(@RequestBody @Validated EmployeeDTO employeeDTO) {
		return this.employeesService.saveEmployee(employeeDTO);
	}


	//---------------------------------------- P U T  ----------------------------------------------

	@PutMapping("/{employeeId}")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee updateEmployee(@PathVariable UUID employeeId, @RequestBody EmployeeDTO employeeDTO) {
		return this.employeesService.updateEmployee(employeeId, employeeDTO);
	}

	//----------------------------------- D E L E T E  ----------------------------------------------

	@DeleteMapping("/{employeeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteEmployee(@PathVariable UUID employeeId) {
		this.employeesService.deleteEmployee(employeeId);
	}
}
