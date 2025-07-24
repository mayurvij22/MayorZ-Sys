package employee.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @NotBlank(message = "Employee ID is required")
    private String empId;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @Pattern(regexp = "^\\d{10}$", message = "Contact must be a valid 10-digit number")
    private String contact;

    @NotBlank(message = "Designation is required")
    private String designation;

    @NotBlank(message = "Manager ID is required")
    private String manager; // Assume this is empId of the manager
}