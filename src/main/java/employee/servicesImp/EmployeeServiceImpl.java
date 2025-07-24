package employee.servicesImp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import employee.dtos.EmployeeRequestDto;
import employee.exceptions.DuplicateResourceException;
import employee.exceptions.ResourceNotFoundException;
import employee.models.Employee;
import employee.repos.EmployeeRepository;
import employee.services.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    private static final String EMP_ID_PATTERN = "^EMP\\d+$";

    private void validateEmpIdFormat(String empId) {
        if (!empId.matches(EMP_ID_PATTERN)) {
            throw new IllegalArgumentException("Employee ID must be in the format 'EMP1', 'EMP2', etc.");
        }
    }

    @Override
    public Employee create(EmployeeRequestDto request) {
        validateEmpIdFormat(request.getEmpId());

        if (repository.existsById(request.getEmpId())) {
            throw new DuplicateResourceException("Employee with ID " + request.getEmpId() + " already exists");
        }

        Employee emp = new Employee(
            request.getEmpId(),
            request.getName(),
            request.getContact(),
            request.getDesignation(),
            request.getManager()
        );
        return repository.save(emp);
    }

    public List<Employee> getAll() {
        List<Employee> employees = repository.findAll();
        if (employees.isEmpty()) {
            throw new ResourceNotFoundException("No employees in database");
        }
        return employees;
    }

    public Employee getById(String empId) {
        validateEmpIdFormat(empId);
        return repository.findById(empId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with ID " + empId + " not found"));
    }

    public void delete(String empId) {
        validateEmpIdFormat(empId);

        if (!repository.existsById(empId)) {
            throw new ResourceNotFoundException("Employee with ID " + empId + " not found");
        }

        repository.deleteById(empId);
    }

    public Employee update(String empId, EmployeeRequestDto request) {
        validateEmpIdFormat(empId);

        Employee existing = getById(empId); // This already validates and throws if not found

        existing.setName(request.getName());
        existing.setContact(request.getContact());
        existing.setDesignation(request.getDesignation());
        existing.setManager(request.getManager());

        return repository.save(existing);
    }
}
