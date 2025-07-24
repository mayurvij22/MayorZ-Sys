package employee.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PaybookRequestDto {

    @NotBlank(message = "Employee ID is required")
    @Pattern(regexp = "^EMP\\d+$", message = "Employee ID must follow the format 'EMP1', 'EMP2', etc.")
    private String empId;

    @NotBlank(message = "Company name is required")
    private String company;

    @Positive(message = "CTC must be greater than 0")
    private double ctc;

    @Positive(message = "Salary must be greater than 0")
    private double salary;
}
