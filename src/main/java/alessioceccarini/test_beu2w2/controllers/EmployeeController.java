package alessioceccarini.test_beu2w2.controllers;


import alessioceccarini.test_beu2w2.entities.Employee;
import alessioceccarini.test_beu2w2.services.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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


}
