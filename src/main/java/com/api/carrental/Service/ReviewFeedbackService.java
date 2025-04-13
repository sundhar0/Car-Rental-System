package com.api.carrental.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.carrental.Repository.ReviewFeedbackRepository;
import com.api.carrental.model.ReviewFeedback;

@Service
public class ReviewFeedbackService {
	
	@Autowired
	private ReviewFeedbackRepository reviewFeedbackRepository;

	public List<ReviewFeedback> getByReview(int cId) {
		
		return reviewFeedbackRepository.findByCustomerId(cId);
	}

}
