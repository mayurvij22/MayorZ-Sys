package employee.dtos;

import lombok.Data;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
public class LeaveRequestDto {

    @NotBlank(message = "Employee ID is required")
    private String empId;

    @NotNull(message = "Start date is required")
    @PastOrPresent(message = "Start date cannot be in the future")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    @FutureOrPresent(message = "End date cannot be in the past")
    private LocalDate endDate;

    @NotBlank(message = "Leave type is required")
    @Size(max = 30, message = "Leave type must not exceed 30 characters")
    private String leaveType;

    @Size(max = 500, message = "Reason must not exceed 500 characters")
    private String reason;

    // Optional: Add a custom validation method (can be used in service layer)
    public boolean isValidDateRange() {
        return startDate != null && endDate != null && !startDate.isAfter(endDate);
    }
}
