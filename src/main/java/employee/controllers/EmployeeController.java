package employee.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import employee.dtos.EmployeeRequestDto;
import employee.models.Employee;
import employee.services.EmployeeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody @Valid EmployeeRequestDto request) {
        Employee createdEmployee = service.create(request);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Employee created successfully");
        response.put("employee", createdEmployee);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public List<Employee> getAll() {
        return service.getAll();
    }

    @GetMapping("/getById/{empId}")
    public ResponseEntity<Employee> getById(@PathVariable String empId) {
        return ResponseEntity.ok(service.getById(empId));
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable String id, @RequestBody @Valid EmployeeRequestDto request) {
        Employee updated = service.update(id, request);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Employee updated successfully");
        response.put("employee", updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<String> delete(@PathVariable String empId) {
        service.delete(empId);
        return ResponseEntity.ok("Employee deleted successfully");
    }

}
