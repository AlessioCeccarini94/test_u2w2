package alessioceccarini.test_beu2w2.services;

import alessioceccarini.test_beu2w2.entities.Trip;
import alessioceccarini.test_beu2w2.payloads.TripDTo;
import alessioceccarini.test_beu2w2.repositories.TripRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TripsService {

	private final TripRepo tripRepo;

	@Autowired
	public TripsService(TripRepo tripRepo) {
		this.tripRepo = tripRepo;
	}

	//------------------------------------ G E T ----------------------------------------------

	public Page<Trip> findAllTrips(int page, int size, String sortBy) {
		if (page < 0) page = 0;
		if (size < 0 || size > 150) size = 10;
		if (sortBy == null) sortBy = "destination";
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
		return tripRepo.findAll(pageable);
	}

	//------------------------------------ P O S T ----------------------------------------------

	public Trip saveTrip(TripDTo tripDTo) {
		Trip newTrip = new Trip(tripDTo.destination(), tripDTo.startDate(), tripDTo.tripState());
		return tripRepo.save(newTrip);
	}
}
