package employee.servicesImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import employee.dtos.PaybookRequestDto;
import employee.exceptions.InvalidInputException;
import employee.exceptions.ResourceNotFoundException;
import employee.models.Paybook;
import employee.repos.PaybookRepository;
import employee.services.PaybookService;

@Service
public class PaybookServiceImpl implements PaybookService {

    @Autowired
    private PaybookRepository repository;

    @Override
    public Paybook create(PaybookRequestDto request) {
        validateRequest(request);

        boolean exists = repository.existsByEmpIdAndCompany(request.getEmpId(), request.getCompany());
        if (exists) {
            throw new InvalidInputException("Paybook entry for this employee and company already exists.");
        }

        Paybook paybook = new Paybook();
        paybook.setEmpId(request.getEmpId());
        paybook.setCompany(request.getCompany());
        paybook.setCtc(request.getCtc());
        paybook.setSalary(request.getSalary());

        return repository.save(paybook);
    }


    @Override
    public List<Paybook> getAll() {
        List<Paybook> all = repository.findAll();
        if (all.isEmpty()) {
            throw new ResourceNotFoundException("No paybook entries found.");
        }
        return all;
    }

    @Override
    public List<Paybook> getByEmpId(String empId) {
        if (!empId.matches("^EMP\\d+$")) {
            throw new InvalidInputException("Employee ID must be in format 'EMP1', 'EMP2', etc.");
        }

        List<Paybook> entries = repository.findByEmpId(empId);
        if (entries.isEmpty()) {
            throw new ResourceNotFoundException("No paybook entries found for employee ID " + empId);
        }
        return entries;
    }

    @Override
    public String delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Paybook entry with ID " + id + " not found");
        }
        repository.deleteById(id);
        return "Paybook entry with ID " + id + " deleted successfully.";
    }


    public Paybook update(Long id, PaybookRequestDto request) {
        validateRequest(request);

        Paybook existing = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Paybook entry with ID " + id + " not found"));

        // Only check for duplicates if empId or company is changing
        if (!existing.getEmpId().equals(request.getEmpId()) || 
            !existing.getCompany().equals(request.getCompany())) {

            boolean exists = repository.existsByEmpIdAndCompany(request.getEmpId(), request.getCompany());
            if (exists) {
                throw new InvalidInputException("Another paybook entry for this employee and company already exists.");
            }
        }

        existing.setEmpId(request.getEmpId());
        existing.setCompany(request.getCompany());
        existing.setCtc(request.getCtc());
        existing.setSalary(request.getSalary());

        return repository.save(existing);
    }

  
    private void validateRequest(PaybookRequestDto request) {
        if (request.getEmpId() == null || !request.getEmpId().matches("^EMP\\d+$")) {
            throw new InvalidInputException("Employee ID must follow the format 'EMP1', 'EMP2', etc.");
        }

        if (request.getCompany() == null || request.getCompany().trim().isEmpty()) {
            throw new InvalidInputException("Company name cannot be blank.");
        }

        if (request.getCtc() <= 0) {
            throw new InvalidInputException("CTC must be a positive number.");
        }

        if (request.getSalary() <= 0) {
            throw new InvalidInputException("Salary must be a positive number.");
        }

        if (request.getSalary() > request.getCtc()) {
            throw new InvalidInputException("Salary cannot be greater than CTC.");
        }
    }

	
}
