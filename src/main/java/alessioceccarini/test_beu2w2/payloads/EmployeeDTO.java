package alessioceccarini.test_beu2w2.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmployeeDTO(
		@NotBlank(message = "please insert username")
		String username,
		@NotBlank(message = "please insert name")
		@Size(min = 3, max = 50)
		String name,
		@NotBlank(message = "please insert surname")
		@Size(min = 3, max = 50)
		String surname,
		@NotBlank(message = "please insert email")
		@Email
		String email) {
}
