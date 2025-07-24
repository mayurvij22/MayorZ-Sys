package employee.repos;

import employee.models.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LeaveRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByEmpId(String empId);
    List<LeaveRequest> findByStatus(String status);
	boolean existsByEmpIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(String empId, LocalDate endDate,
			LocalDate startDate);
}
