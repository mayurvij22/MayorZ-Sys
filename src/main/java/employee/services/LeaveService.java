package employee.services;

import employee.dtos.LeaveRequestDto;
import employee.models.LeaveRequest;

import java.util.List;

public interface LeaveService {
    LeaveRequest applyLeave(LeaveRequestDto request);
    List<LeaveRequest> getAllLeaves();
    List<LeaveRequest> getLeavesByEmpId(String empId);
    LeaveRequest updateStatus(Long id, String status);
	void deleteLeave(Long id);
	LeaveRequest updateLeave(Long id, LeaveRequestDto dto);
}
