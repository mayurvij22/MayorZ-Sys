package employee.servicesImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import employee.dtos.FeedbackRequestDto;
import employee.exceptions.ResourceNotFoundException;
import employee.models.Feedback;
import employee.repos.FeedbackRepository;
import employee.services.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository repository;

    @Override
    public Feedback submitFeedback(FeedbackRequestDto request) {
        // Check for duplicate (same empId and message)
        Optional<Feedback> existing = repository.findByEmpIdAndMessage(request.getEmpId(), request.getMessage());
        if (existing.isPresent()) {
            throw new IllegalArgumentException("Duplicate feedback already exists for this employee");
        }

        Feedback feedback = new Feedback();
        feedback.setEmpId(request.getEmpId());
        feedback.setMessage(request.getMessage());

        return repository.save(feedback);
    }

    @Override
    public List<Feedback> getAll() {
        List<Feedback> list = repository.findAll();
        if (list.isEmpty()) {
            throw new ResourceNotFoundException("No feedback records found");
        }
        return list;
    }

    @Override
    public List<Feedback> getByEmpId(String empId) {
        List<Feedback> list = repository.findByEmpId(empId);
        if (list.isEmpty()) {
            throw new ResourceNotFoundException("No feedback found for employee ID: " + empId);
        }
        return list;
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Feedback with ID " + id + " not found");
        }
        repository.deleteById(id);
    }

    @Override
    public Feedback updateFeedback(Long id, FeedbackRequestDto request) {
        Feedback feedback = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback with ID " + id + " not found"));

        feedback.setEmpId(request.getEmpId());
        feedback.setMessage(request.getMessage());

        return repository.save(feedback);
    }
}
