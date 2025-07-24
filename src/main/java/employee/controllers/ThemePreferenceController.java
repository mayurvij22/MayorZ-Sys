package employee.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import employee.dtos.ThemePreferenceRequestDto;
import employee.models.ThemePreference;
import employee.services.ThemePreferenceService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/theme")
public class ThemePreferenceController {

    @Autowired
    private ThemePreferenceService service;

    @PutMapping
    public ResponseEntity<ThemePreference> saveOrUpdate(@RequestBody @Valid ThemePreferenceRequestDto request) {
        return ResponseEntity.ok(service.saveOrUpdate(request));
    }

    @GetMapping("/{empId}")
    public ResponseEntity<ThemePreference> getByEmpId(@PathVariable String empId) {
        return ResponseEntity.ok(service.getByEmpId(empId));
    }

    @DeleteMapping("/{empId}")
    public ResponseEntity<?> delete(@PathVariable String empId) {
        service.delete(empId);
        return ResponseEntity.noContent().build();
    }
}
