package employee.services;

import java.util.List;


import employee.dtos.PaybookRequestDto;
import employee.models.Paybook;

public interface PaybookService {
    Paybook create(PaybookRequestDto request);
    List<Paybook> getAll();
    List<Paybook> getByEmpId(String empId);
    String delete(Long id);
    Paybook update(Long id, PaybookRequestDto request);
}