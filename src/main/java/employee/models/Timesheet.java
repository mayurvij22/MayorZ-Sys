package employee.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"empId", "weekRange"}))
public class Timesheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Employee ID is required")
    private String empId;

    @NotBlank(message = "Week range is required")
    private String weekRange; // Format: 01-07

    @Min(value = 0, message = "Working days must be >= 0")
    @Max(value = 7, message = "Working days must be <= 7")
    private int workingDays;

    private LocalDate leaveDate; // Optional
}
