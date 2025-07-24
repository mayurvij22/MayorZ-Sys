package employee.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import employee.dtos.ApiResponse;

import employee.dtos.FeedbackRequestDto;
import employee.models.Feedback;
import employee.services.FeedbackService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService service;

    @PostMapping("/submit")
    public ResponseEntity<?> submit(@RequestBody @Valid FeedbackRequestDto request) {
        Feedback feedback = service.submitFeedback(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse(true, "Feedback submitted successfully", feedback)
        );
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        List<Feedback> list = service.getAll();
        return ResponseEntity.ok(
                new ApiResponse(true, "All feedback records fetched successfully", list)
        );
    }

    @GetMapping("/getByEmpId/{empId}")
    public ResponseEntity<?> getByEmpId(@PathVariable String empId) {
        List<Feedback> list = service.getByEmpId(empId);
        return ResponseEntity.ok(
                new ApiResponse(true, "Feedback fetched for employee: " + empId, list)
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(
                new ApiResponse(true, "Feedback deleted successfully", null)
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody FeedbackRequestDto request) {
        Feedback updated = service.updateFeedback(id, request);
        return ResponseEntity.ok(
                new ApiResponse(true, "Feedback updated successfully", updated)
        );
    }

 
}
