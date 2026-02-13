package alessioceccarini.test_beu2w2.entities;


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
@Table(name = "employes")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(unique = true, nullable = false, name = "employee_ID")
	private UUID id;
	private String username;
	private String name;
	private String surname;
	private String email;
	private String profileImg;


	public Employee(String username, String name, String surname, String email) {

		this.username = username;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.profileImg = "https://ui-avatars.com/api/?name=" + name + surname;
	}
}
