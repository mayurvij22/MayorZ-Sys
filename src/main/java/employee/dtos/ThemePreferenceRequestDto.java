package employee.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ThemePreferenceRequestDto {

    @NotBlank(message = "Employee ID is required")
    private String empId;

    @Pattern(regexp = "red|blue|green|yellow|black", message = "Invalid color selected")
    private String color;
}

