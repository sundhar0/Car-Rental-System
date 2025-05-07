package com.api.carrental.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Service.ReviewFeedbackService;
import com.api.carrental.model.ReviewFeedback;

@RestController
@RequestMapping("/api/review")
public class ReviewFeedbackController {

	@Autowired
	private ReviewFeedbackService reviewFeedBackService;
	
	@PostMapping("/add/{cId}/{cusId}")
	public Object addReview(@PathVariable int cId,@PathVariable Long cusId,@RequestBody ReviewFeedback reviewFeedback) throws InvalidIDException {
		return reviewFeedBackService.addReview(cId,cusId,reviewFeedback);
	}
	
	@GetMapping("/getByRating/{rating}")
	public List<ReviewFeedback> ShowByRating(@PathVariable int rating) {
		return reviewFeedBackService.getByRating(rating);
	}
	@GetMapping("/getByCusId/{cId}")
	public List<ReviewFeedback> ShowByCustomerId(@PathVariable Long cId) throws InvalidIDException {
		return reviewFeedBackService.getByReview(cId);
	}
}

