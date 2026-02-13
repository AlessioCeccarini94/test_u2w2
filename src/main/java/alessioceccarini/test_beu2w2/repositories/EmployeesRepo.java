package alessioceccarini.test_beu2w2.repositories;

import alessioceccarini.test_beu2w2.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmployeesRepo extends JpaRepository<Employee, UUID> {
	Optional<Employee> findByEmail(String email);
}
