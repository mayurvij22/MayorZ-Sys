package employee.services;

import java.util.List;


import employee.dtos.FeedbackRequestDto;
import employee.models.Feedback;

public interface FeedbackService {
	Feedback submitFeedback(FeedbackRequestDto request);
	List<Feedback> getAll();
	List<Feedback> getByEmpId(String empId);
	void delete(Long id);
	Feedback updateFeedback(Long id, FeedbackRequestDto request);

}

