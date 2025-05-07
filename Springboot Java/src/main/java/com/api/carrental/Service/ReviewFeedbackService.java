package com.api.carrental.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Repository.ReviewFeedbackRepository;
import com.api.carrental.model.Car;
import com.api.carrental.model.Customer;
import com.api.carrental.model.ReviewFeedback;

@Service
public class ReviewFeedbackService {

	@Autowired
	private ReviewFeedbackRepository reviewFeedbackRepository;
	
	@Autowired
	private CarService carService;
	@Autowired
	private CustomerService customerService;

	public List<ReviewFeedback> getByReview(int cId) throws InvalidIDException {
		//this method will be used to show the feedback about the customer using the customer
		Customer customer=customerService.getSingleCustomer(cId);
		return reviewFeedbackRepository.findByCustomerId(cId);
	}

	public Object addReview(int cId, int cusId, ReviewFeedback reviewFeedback) throws InvalidIDException {
		//this method is for adding the feedback about the customer and the car
		Car car=carService.getById(cId);
		if(car==null)
			throw new InvalidIDException("Given Car id is Invalid...");
		//getting the details about the car using its id
		Customer customer=customerService.getSingleCustomer(cusId);
		if(customer==null)
			throw new InvalidIDException("Given Customer Id is Invalid...");
		//getting details about the customer using its id
		reviewFeedback.setCar(car);
		//setting the values
		reviewFeedback.setCustomer(customer);
		return reviewFeedbackRepository.save(reviewFeedback);
	}

	public List<ReviewFeedback> getByRating(int rating) {
		//this method is for showing by the level of rating
		return reviewFeedbackRepository.getByRating(rating);
	}

}
