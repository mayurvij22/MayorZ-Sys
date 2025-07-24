package employee.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import employee.models.Paybook;

@Repository
public interface PaybookRepository extends JpaRepository<Paybook, Long> {
    List<Paybook> findByEmpId(String empId);

	boolean existsByEmpIdAndCompany(String empId, String company);
}
