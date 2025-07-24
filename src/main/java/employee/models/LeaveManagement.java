package employee.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveManagement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Employee ID is required")
    @Column(nullable = false)
    private String empId;

    @NotNull(message = "Leave date is required")
    @PastOrPresent(message = "Leave date cannot be in the future")
    @Column(nullable = false)
    private LocalDate leaveDate;
}
