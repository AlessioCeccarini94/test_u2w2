package alessioceccarini.test_beu2w2.services;

import alessioceccarini.test_beu2w2.entities.Employee;
import alessioceccarini.test_beu2w2.exceptions.AlreadyUsedException;
import alessioceccarini.test_beu2w2.exceptions.NotFoundException;
import alessioceccarini.test_beu2w2.payloads.EmployeeDTO;
import alessioceccarini.test_beu2w2.repositories.EmployeesRepo;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class EmployeesService {

	private final EmployeesRepo employeesRepo;
	private final Cloudinary cloudinary;

	@Autowired
	public EmployeesService(EmployeesRepo employeesRepo, Cloudinary cloudinary) {
		this.employeesRepo = employeesRepo;
		this.cloudinary = cloudinary;
	}

	//------------------------------------- G E T ----------------------------------------------

	public Page<Employee> findAllEmployees(int page, int size, String sortBy) {
		if (page < 0) page = 0;
		if (size < 0 || size > 150) size = 10;
		if (sortBy == null) sortBy = "name";
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
		return this.employeesRepo.findAll(pageable);
	}

	public Employee findEmloyeeById(UUID employeeId) {
		return this.employeesRepo.findById(employeeId).orElseThrow(() -> new NotFoundException("User Not Found"));
	}

	//------------------------------------- P O S T ----------------------------------------------

	public Employee saveEmployee(EmployeeDTO employeeDTO) {
		this.employeesRepo.findByEmail(employeeDTO.email()).ifPresent(employee -> {
			throw new AlreadyUsedException("User " + employeeDTO.email() + " already exists");
		});
		Employee newEmployee = new Employee(
				employeeDTO.username(),
				employeeDTO.name(),
				employeeDTO.surname(),
				employeeDTO.email());
		return this.employeesRepo.save(newEmployee);
	}

	//-------------------------------------- P U T  ----------------------------------------------

	public Employee updateEmployee(UUID id, EmployeeDTO employeeDTO) {
		Employee employee = this.findEmloyeeById(id);
		employee.setUsername(employeeDTO.username());
		employee.setName(employeeDTO.name());
		employee.setSurname(employeeDTO.surname());
		employee.setEmail(employeeDTO.email());
		return this.employeesRepo.save(employee);
	}

	//----------------------------------- D E L E T E  ----------------------------------------------

	public void deleteEmployee(UUID id) {
		this.employeesRepo.deleteById(id);
	}

	//------------------------------------ P A T C H ----------------------------------------------

	public String uploadImage(UUID employeeID, MultipartFile file) throws IOException {
		Employee employee = this.findEmloyeeById(employeeID);
		String profileImgUrl = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
		employee.setProfileImg(profileImgUrl);
		return employeesRepo.save(employee).getProfileImg();
	}
}

