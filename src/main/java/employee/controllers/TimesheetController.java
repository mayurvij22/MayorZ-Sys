package employee.controllers;

import employee.dtos.TimesheetRequestDto;
import employee.models.Timesheet;
import employee.services.TimesheetService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/timesheet")
public class TimesheetController {

    @Autowired
    private TimesheetService service;

    
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody TimesheetRequestDto request) {
        Timesheet created = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message", "Timesheet created successfully",
                "data", created
        ));
    }

  
    @GetMapping("/getAll")
    public ResponseEntity<Map<String, Object>> getAll() {
        List<Timesheet> timesheets = service.getAll();
        return ResponseEntity.ok(Map.of(
                "message", "Fetched all timesheets",
                "data", timesheets
        ));
    }

   
    @GetMapping("/getByEmpId/{empId}")
    public ResponseEntity<Map<String, Object>> getByEmpId(@PathVariable String empId) {
        List<Timesheet> timesheets = service.getByEmpId(empId);
        return ResponseEntity.ok(Map.of(
                "message", "Fetched timesheets for employee ID: " + empId,
                "data", timesheets
        ));
    }

   
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id,
                                                      @Valid @RequestBody TimesheetRequestDto request) {
        Timesheet updated = service.update(id, request);
        return ResponseEntity.ok(Map.of(
                "message", "Timesheet updated successfully",
                "data", updated
        ));
    }

  
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
        String message = service.delete(id);
        return ResponseEntity.ok(Map.of("message", message));
    }

 
    @GetMapping("/summary/{empId}/{year}/{month}")
    public ResponseEntity<Map<String, Object>> getMonthlySummary(@PathVariable String empId,
                                                                 @PathVariable int year,
                                                                 @PathVariable int month) {
        int workingDays = service.getTotalWorkingDaysForMonth(empId, year, month);
        int totalLeaves = service.getTotalLeavesForMonth(empId, year, month);
        return ResponseEntity.ok(Map.of(
                "message", "Monthly summary fetched",
                "empId", empId,
                "year", year,
                "month", month,
                "totalWorkingDays", workingDays,
                "totalLeaves", totalLeaves
        ));
    }
}
