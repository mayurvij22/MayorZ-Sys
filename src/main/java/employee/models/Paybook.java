package employee.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paybook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Employee ID is required")
    @Pattern(regexp = "^EMP\\d+$", message = "Employee ID must follow the format 'EMP1', 'EMP2', etc.")
    private String empId;

    @NotBlank(message = "Company name is required")
    private String company;

    @Positive(message = "CTC must be a positive number")
    private double ctc;

    @Positive(message = "Salary must be a positive number")
    private double salary;
}
