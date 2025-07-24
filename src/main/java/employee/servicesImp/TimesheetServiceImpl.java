package employee.servicesImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import employee.dtos.TimesheetRequestDto;
import employee.exceptions.ResourceNotFoundException;
import employee.models.Timesheet;
import employee.repos.TimesheetRepository;
import employee.services.TimesheetService;

@Service
public class TimesheetServiceImpl implements TimesheetService {

    @Autowired
    private TimesheetRepository repository;

    @Override
    public Timesheet create(TimesheetRequestDto request) {
        repository.findByEmpIdAndWeekRange(request.getEmpId(), request.getWeekRange())
                .ifPresent(t -> {
                    throw new IllegalArgumentException("Timesheet already exists for this employee and week range.");
                });

        Timesheet timesheet = new Timesheet();
        timesheet.setEmpId(request.getEmpId());
        timesheet.setWeekRange(request.getWeekRange());
        timesheet.setWorkingDays(request.getWorkingDays());
        timesheet.setLeaveDate(request.getLeaveDate());

        return repository.save(timesheet);
    }

    @Override
    public Timesheet update(Long id, TimesheetRequestDto request) {
        Timesheet existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Timesheet not found with ID " + id));

        existing.setWeekRange(request.getWeekRange());
        existing.setWorkingDays(request.getWorkingDays());
        existing.setLeaveDate(request.getLeaveDate());

        return repository.save(existing);
    }

  
    @Override
    public List<Timesheet> getAll() {
        List<Timesheet> timesheets = repository.findAll();
        if (timesheets.isEmpty()) {
            throw new ResourceNotFoundException("No timesheet records found");
        }
        return timesheets;
    }


    @Override
    public List<Timesheet> getByEmpId(String empId) {
        List<Timesheet> timesheets = repository.findByEmpId(empId);
        if (timesheets.isEmpty()) {
            throw new ResourceNotFoundException("No timesheets found for employee ID: " + empId);
        }
        return timesheets;
    }


    @Override
    public String delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Timesheet not found with ID " + id);
        }
        repository.deleteById(id);
        return "Timesheet with ID " + id + " deleted successfully";
    }

    @Override
    public int getTotalWorkingDaysForMonth(String empId, int year, int month) {
        return repository.findByEmpId(empId).stream()
                .filter(t -> t.getLeaveDate() != null &&
                        t.getLeaveDate().getYear() == year &&
                        t.getLeaveDate().getMonthValue() == month)
                .mapToInt(Timesheet::getWorkingDays)
                .sum();
    }

    @Override
    public int getTotalLeavesForMonth(String empId, int year, int month) {
        return (int) repository.findByEmpId(empId).stream()
                .filter(t -> t.getLeaveDate() != null &&
                        t.getLeaveDate().getYear() == year &&
                        t.getLeaveDate().getMonthValue() == month)
                .count();
    }
}
