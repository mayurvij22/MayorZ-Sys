package employee.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Employee ID must not be blank")
    @Size(max = 50, message = "Employee ID must not exceed 50 characters")
    private String empId;

    @NotBlank(message = "Feedback message must not be blank")
    @Size(min = 10, max = 1000, message = "Message must be between 10 and 1000 characters")
    @Column(length = 1000)
    private String message;
}
