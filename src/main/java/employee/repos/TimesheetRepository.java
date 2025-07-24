package employee.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import employee.models.Timesheet;
import java.util.List;
import java.util.Optional;

public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {
    List<Timesheet> findByEmpId(String empId);
    Optional<Timesheet> findByEmpIdAndWeekRange(String empId, String weekRange);
}
