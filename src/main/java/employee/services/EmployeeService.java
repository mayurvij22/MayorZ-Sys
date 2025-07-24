package employee.services;

import java.util.List;

import employee.dtos.EmployeeRequestDto;
import employee.models.Employee;

public interface EmployeeService {
    Employee create(EmployeeRequestDto request);
    List<Employee> getAll();
    Employee getById(String empId);
    Employee update(String id, EmployeeRequestDto request);
    void delete(String empId);
}