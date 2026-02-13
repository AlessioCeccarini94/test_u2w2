package alessioceccarini.test_beu2w2.entities;

import alessioceccarini.test_beu2w2.enums.TripState;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;


@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "trips")
public class Trip {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String destination;
	private String startTime;
	private TripState state;

	public Trip(String destination, String startTime, TripState state) {
		this.destination = destination;
		this.startTime = startTime;
		this.state = state;
	}
}
