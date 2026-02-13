package alessioceccarini.test_beu2w2.payloads;

import alessioceccarini.test_beu2w2.enums.TripState;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record TripDTo(
		@NotBlank(message = "insert destination")
		String destination,
		@NotNull(message = "insert departure date")
		LocalDate startDate,
		@NotNull(message = "insert state")
		TripState tripState) {
}
