package employee.servicesImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import employee.dtos.ThemePreferenceRequestDto;
import employee.exceptions.ResourceNotFoundException;
import employee.models.ThemePreference;
import employee.repos.ThemePreferenceRepository;
import employee.services.ThemePreferenceService;

@Service
public class ThemePreferenceServiceImpl implements ThemePreferenceService {

    @Autowired
    private ThemePreferenceRepository repository;

    @Override
    public ThemePreference saveOrUpdate(ThemePreferenceRequestDto request) {
        ThemePreference theme = new ThemePreference();
        theme.setEmpId(request.getEmpId());
        theme.setColor(request.getColor());

        return repository.save(theme);
    }

    @Override
    public ThemePreference getByEmpId(String empId) {
        return repository.findById(empId)
                .orElseThrow(() -> new ResourceNotFoundException("Theme not set for this employee"));
    }

    @Override
    public void delete(String empId) {
        repository.deleteById(empId);
    }
}
