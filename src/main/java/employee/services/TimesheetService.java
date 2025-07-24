package employee.services;

import java.util.List;
import employee.dtos.TimesheetRequestDto;
import employee.models.Timesheet;

public interface TimesheetService {
    Timesheet create(TimesheetRequestDto request);
    Timesheet update(Long id, TimesheetRequestDto request);
    List<Timesheet> getAll();
    List<Timesheet> getByEmpId(String empId);
    String delete(Long id);
    int getTotalWorkingDaysForMonth(String empId, int year, int month);
    int getTotalLeavesForMonth(String empId, int year, int month);
}
