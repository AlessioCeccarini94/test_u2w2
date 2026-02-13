package alessioceccarini.test_beu2w2.payloads;

import java.time.LocalDate;

public record ErrorDTO(String message, LocalDate timestamp) {
}
