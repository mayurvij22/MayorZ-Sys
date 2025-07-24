package employee.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import employee.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
}