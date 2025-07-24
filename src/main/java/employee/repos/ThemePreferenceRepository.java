package employee.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import employee.models.ThemePreference;

@Repository
public interface ThemePreferenceRepository extends JpaRepository<ThemePreference, String> {
}
