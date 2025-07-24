package employee.servicesImp;

import employee.dtos.LeaveRequestDto;
import employee.models.LeaveRequest;
import employee.repos.LeaveRepository;
import employee.services.LeaveService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveRepository repo;

    @Override
    @Transactional
    public LeaveRequest applyLeave(LeaveRequestDto dto) {
        validateLeaveDto(dto);

        // Check for overlapping leave request
        boolean exists = repo.existsByEmpIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                dto.getEmpId(), dto.getEndDate(), dto.getStartDate());
        if (exists) {
            throw new IllegalStateException("A leave request already exists for the given date range");
        }

        LeaveRequest leave = new LeaveRequest();
        leave.setEmpId(dto.getEmpId());
        leave.setStartDate(dto.getStartDate());
        leave.setEndDate(dto.getEndDate());
        leave.setLeaveType(dto.getLeaveType());
        leave.setReason(dto.getReason());
        leave.setStatus("Pending");

        return repo.save(leave);
    }

    @Override
    public List<LeaveRequest> getAllLeaves() {
        List<LeaveRequest> leaves = repo.findAll();
        if (leaves.isEmpty()) {
            throw new RuntimeException("No leave records found");
        }
        return leaves;
    }

    @Override
    public List<LeaveRequest> getLeavesByEmpId(String empId) {
        if (empId == null || empId.isEmpty()) {
            throw new IllegalArgumentException("Employee ID cannot be null or empty");
        }
        List<LeaveRequest> leaves = repo.findByEmpId(empId);
        if (leaves.isEmpty()) {
            throw new RuntimeException("No leaves found for employee ID: " + empId);
        }
        return leaves;
    }

    @Override
    @Transactional
    public LeaveRequest updateStatus(Long id, String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status must not be empty");
        }

        LeaveRequest leave = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Leave not found with id: " + id));

        leave.setStatus(status);
        return repo.save(leave);
    }

    @Override
    @Transactional
    public void deleteLeave(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Leave not found with id: " + id);
        }
        repo.deleteById(id);
    }

    @Override
    @Transactional
    public LeaveRequest updateLeave(Long id, LeaveRequestDto dto) {
        validateLeaveDto(dto);

        LeaveRequest leave = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave not found with id: " + id));

        // Check for overlap with other leaves for same employee (excluding current leave)
        boolean duplicate = repo.existsByEmpIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                dto.getEmpId(), dto.getEndDate(), dto.getStartDate());

        if (duplicate && !(leave.getStartDate().equals(dto.getStartDate())
                && leave.getEndDate().equals(dto.getEndDate()))) {
            throw new IllegalStateException("Updated dates overlap with another existing leave for this employee");
        }

        leave.setStartDate(dto.getStartDate());
        leave.setEndDate(dto.getEndDate());
        leave.setLeaveType(dto.getLeaveType());
        leave.setReason(dto.getReason());

        return repo.save(leave);
    }

    // ✅ Common DTO validation logic
    private void validateLeaveDto(LeaveRequestDto dto) {
        if (dto.getEmpId() == null || dto.getEmpId().isEmpty()) {
            throw new IllegalArgumentException("Employee ID is required");
        }
        if (dto.getStartDate() == null || dto.getEndDate() == null) {
            throw new IllegalArgumentException("Start and End dates are required");
        }
        if (dto.getEndDate().isBefore(dto.getStartDate())) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }
        if (dto.getLeaveType() == null || dto.getLeaveType().isEmpty()) {
            throw new IllegalArgumentException("Leave type is required");
        }
        if (dto.getReason() == null || dto.getReason().isEmpty()) {
            throw new IllegalArgumentException("Leave reason is required");
        }
    }
}
