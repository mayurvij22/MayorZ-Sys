package employee.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class TimesheetRequestDto {

    @NotBlank(message = "Employee ID is required")
    private String empId;

    @NotBlank(message = "Week range is required")
    private String weekRange;

    @Min(value = 0, message = "Working days must be >= 0")
    @Max(value = 7, message = "Working days must be <= 7")
    private int workingDays;

    private LocalDate leaveDate; // Optional
}
