package employee.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import employee.dtos.PaybookRequestDto;
import employee.models.Paybook;
import employee.services.PaybookService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/paybook")
public class PaybookController {

    @Autowired
    private PaybookService service;

    
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody @Valid PaybookRequestDto request) {
        Paybook created = service.create(request);
        return new ResponseEntity<>(
            Map.of(
                "message", "Paybook created successfully",
                "data", created
            ),
            HttpStatus.CREATED
        );
    }

  
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @RequestBody @Valid PaybookRequestDto request) {
        Paybook updated = service.update(id, request);
        return ResponseEntity.ok(
            Map.of(
                "message", "Paybook updated successfully",
                "data", updated
            )
        );
    }

   
    @GetMapping("/getAll")
    public ResponseEntity<List<Paybook>> getAll() {
        List<Paybook> list = service.getAll();
        return ResponseEntity.ok(list);
    }

    
    @GetMapping("/getByEmpId/{empId}")
    public ResponseEntity<List<Paybook>> getByEmpId(@PathVariable String empId) {
        List<Paybook> list = service.getByEmpId(empId);
        return ResponseEntity.ok(list);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
        String message = service.delete(id);
        return new ResponseEntity<>(
            Map.of("message", message),
            HttpStatus.OK
        );
    }
}
