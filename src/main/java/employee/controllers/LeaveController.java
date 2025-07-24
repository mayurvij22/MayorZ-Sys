package employee.controllers;

import employee.dtos.LeaveRequestDto;
import employee.models.LeaveRequest;
import employee.services.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {

    @Autowired
    private LeaveService service;

   
    @PostMapping("/apply")
    public ResponseEntity<?> apply(@RequestBody @Valid LeaveRequestDto dto) {
        try {
            LeaveRequest leave = service.applyLeave(dto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Leave request submitted successfully: ID = " + leave.getId());
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body("Leave request failed: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while submitting leave request");
        }
    }

    
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        try {
            List<LeaveRequest> leaves = service.getAllLeaves();
            return ResponseEntity.ok(leaves);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // ✅ Get leaves by employee ID
    @GetMapping("/getByEmpId/{empId}")
    public ResponseEntity<?> getByEmpId(@PathVariable String empId) {
        try {
            List<LeaveRequest> leaves = service.getLeavesByEmpId(empId);
            return ResponseEntity.ok(leaves);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // ✅ Update leave status (admin)
    @PutMapping("/updateStatus/{id}/status/{status}")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @PathVariable String status) {
        try {
            LeaveRequest updated = service.updateStatus(id, status);
            return ResponseEntity.ok("Leave status updated to '" + status + "' for ID: " + id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // ✅ Update existing leave (employee/admin)
    @PutMapping("/updateLeave/{id}")
    public ResponseEntity<?> updateLeave(@PathVariable Long id, @RequestBody @Valid LeaveRequestDto dto) {
        try {
            LeaveRequest updated = service.updateLeave(id, dto);
            return ResponseEntity.ok("Leave request updated successfully: ID = " + updated.getId());
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // ✅ Delete leave
    @DeleteMapping("/deleteLeave/{id}")
    public ResponseEntity<?> deleteLeave(@PathVariable Long id) {
        try {
            service.deleteLeave(id);
            return ResponseEntity.ok("Leave request deleted successfully for ID: " + id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
