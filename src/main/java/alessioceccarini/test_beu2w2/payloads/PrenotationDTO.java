package alessioceccarini.test_beu2w2.payloads;

import java.time.LocalDate;
import java.util.UUID;

public record PrenotationDTO(LocalDate requestDate, String specialRequest, UUID employeeId, UUID tripId) {
}
