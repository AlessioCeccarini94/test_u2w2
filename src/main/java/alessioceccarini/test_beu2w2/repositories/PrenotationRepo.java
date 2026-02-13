package alessioceccarini.test_beu2w2.repositories;


import alessioceccarini.test_beu2w2.entities.Employee;
import alessioceccarini.test_beu2w2.entities.Prenotation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface PrenotationRepo extends JpaRepository<Prenotation, UUID> {
	Optional<Prenotation> findByEmployeeId(UUID employeeId);

	Optional<Prenotation> findByTripId(UUID tripId);

	boolean existsByEmployeeAndRequestDate(Employee employee, LocalDate requestDate);
}
