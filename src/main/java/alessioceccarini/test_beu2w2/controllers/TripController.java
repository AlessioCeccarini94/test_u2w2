package alessioceccarini.test_beu2w2.controllers;


import alessioceccarini.test_beu2w2.entities.Trip;
import alessioceccarini.test_beu2w2.payloads.TripDTo;
import alessioceccarini.test_beu2w2.services.TripsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("trips")
public class TripController {

	private final TripsService tripsService;

	@Autowired
	public TripController(TripsService tripsService) {
		this.tripsService = tripsService;
	}

	//------------------------------------ G E T ----------------------------------------------

	@GetMapping
	public Page<Trip> getTrips(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "destination") String orderBY) {
		return tripsService.findAllTrips(page, size, orderBY);
	}

	//------------------------------------ P O S T  ----------------------------------------------

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Trip createTrip(@RequestBody @Validated TripDTo tripDTO) {
		return tripsService.saveTrip(tripDTO);
	}

	//---------------------------------------- P U T  ----------------------------------------------

	@PutMapping("/{tripId}")
	@ResponseStatus(HttpStatus.CREATED)
	public Trip updateTrip(@PathVariable UUID tripId, @RequestBody TripDTo tripDTO) {
		return tripsService.updateTrip(tripId, tripDTO);
	}

	//----------------------------------- D E L E T E  ----------------------------------------------

	@DeleteMapping("/{tripId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTrip(@PathVariable UUID tripId) {
		tripsService.deleteTrip(tripId);
	}
}
