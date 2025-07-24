package employee.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Employee ID is required")
    @Column(nullable = false)
    private String empId;

    @NotNull(message = "Start date is required")
    @Column(nullable = false)
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    @Column(nullable = false)
    private LocalDate endDate;

    @NotBlank(message = "Leave type is required")
    @Size(max = 30, message = "Leave type must not exceed 30 characters")
    @Column(nullable = false)
    private String leaveType; // e.g., Casual, Sick

    @Size(max = 500, message = "Reason must not exceed 500 characters")
    private String reason;

    @Pattern(regexp = "Pending|Approved|Rejected", message = "Status must be Pending, Approved, or Rejected")
    @Column(nullable = false)
    private String status = "Pending"; // Valid values only
}
