package alessioceccarini.test_beu2w2.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;


@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "prenotations")
public class Prenotation {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@Setter
	private LocalDate requestDate;
	@Setter
	private String specialRequest;
	@ManyToOne
	@JoinColumn(name = "trip_id")
	@Setter
	private Trip trip;
	@ManyToOne
	@JoinColumn(name = "employee_id")
	@Setter
	private Employee employee;

	public Prenotation(LocalDate requestDate, String specialRequest) {
		this.requestDate = requestDate;
		this.specialRequest = specialRequest;
	}
}

