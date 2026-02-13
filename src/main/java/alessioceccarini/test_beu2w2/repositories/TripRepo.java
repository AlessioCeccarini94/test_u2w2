package alessioceccarini.test_beu2w2.repositories;

import alessioceccarini.test_beu2w2.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TripRepo extends JpaRepository<Trip, UUID> {
}
