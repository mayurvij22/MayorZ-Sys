package employee.services;


import employee.dtos.ThemePreferenceRequestDto;
import employee.models.ThemePreference;

public interface ThemePreferenceService {
    ThemePreference saveOrUpdate(ThemePreferenceRequestDto request);
    ThemePreference getByEmpId(String empId);
    void delete(String empId);
}