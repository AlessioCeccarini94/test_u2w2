package alessioceccarini.test_beu2w2.services;

import alessioceccarini.test_beu2w2.entities.Employee;
import alessioceccarini.test_beu2w2.entities.Prenotation;
import alessioceccarini.test_beu2w2.entities.Trip;
import alessioceccarini.test_beu2w2.exceptions.NotFoundException;
import alessioceccarini.test_beu2w2.payloads.PrenotationDTO;
import alessioceccarini.test_beu2w2.repositories.EmployeesRepo;
import alessioceccarini.test_beu2w2.repositories.PrenotationRepo;
import alessioceccarini.test_beu2w2.repositories.TripRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PrenotationService {

	private final PrenotationRepo prenotationRepo;
	private final EmployeesRepo employeesRepo;
	private final TripRepo tripRepo;

	@Autowired
	public PrenotationService(PrenotationRepo prenotationRepo, EmployeesRepo employeesRepo, TripRepo tripRepo) {
		this.prenotationRepo = prenotationRepo;
		this.employeesRepo = employeesRepo;
		this.tripRepo = tripRepo;


	}

	//------------------------------------ G E T ----------------------------------------------

	public Page<Prenotation> findAllPrenotations(int page, int size, String sortBy) {
		if (page < 0) page = 0;
		if (size < 0 || size > 150) size = 10;
		if (sortBy == null) sortBy = "requestDate";
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return prenotationRepo.findAll(pageable);
	}

	public Prenotation findPrenotationById(UUID prenotation) {
		return this.prenotationRepo.findById(prenotation).orElseThrow(() -> new NotFoundException("User Not Found"));
	}

	//------------------------------------ P O S T ----------------------------------------------

	public Prenotation savePrenotation(PrenotationDTO prenotationDTO) {

		Employee employee = employeesRepo.findById(prenotationDTO.employeeId())
				.orElseThrow(() -> new NotFoundException("Employee Not Found"));

		Trip trip = tripRepo.findById(prenotationDTO.tripId())
				.orElseThrow(() -> new NotFoundException("Trip Not Found"));

		if (prenotationRepo.existsByEmployeeAndRequestDate(employee, prenotationDTO.requestDate())) {
			throw new RuntimeException("Prenotation already exists");
		}
		Prenotation prenotation = new Prenotation();
		prenotation.setEmployee(employee);
		prenotation.setTrip(trip);
		prenotation.setRequestDate(prenotationDTO.requestDate());
		prenotation.setSpecialRequest(prenotationDTO.specialRequest());
		prenotationRepo.save(prenotation);


		return this.prenotationRepo.save(prenotation);
	}
}
