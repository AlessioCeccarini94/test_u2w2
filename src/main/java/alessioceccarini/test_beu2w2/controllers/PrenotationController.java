package alessioceccarini.test_beu2w2.controllers;

import alessioceccarini.test_beu2w2.entities.Prenotation;
import alessioceccarini.test_beu2w2.payloads.PrenotationDTO;
import alessioceccarini.test_beu2w2.services.PrenotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RequestMapping("prenotations")
@RestController
public class PrenotationController {

	private final PrenotationService prenotationService;

	@Autowired
	public PrenotationController(PrenotationService prenotationService) {
		this.prenotationService = prenotationService;
	}


	//------------------------------------ G E T ----------------------------------------------
	@GetMapping
	public Page<Prenotation> getEmployee(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "surname") String orderBY) {
		return prenotationService.findAllPrenotations(page, size, orderBY);
	}

	//------------------------------------ P O S T  ----------------------------------------------

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Prenotation addEmployee(@RequestBody @Validated PrenotationDTO prenotationDTO) {
		return this.prenotationService.savePrenotation(prenotationDTO);
	}

}
