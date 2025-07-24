package employee.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThemePreference {

    @Id
    private String empId; // primary key (1:1 with Employee)

    @NotBlank
    private String color; // expected values: red, blue, green, yellow, black
}
