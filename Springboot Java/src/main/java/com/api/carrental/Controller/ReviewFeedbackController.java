package com.api.carrental.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Service.CarService;
import com.api.carrental.Service.CustomerService;
import com.api.carrental.Service.RentalService;
import com.api.carrental.Service.ReviewFeedbackService;
import com.api.carrental.model.Car;
import com.api.carrental.model.Customer;
import com.api.carrental.model.Rental;
import com.api.carrental.model.ReviewFeedback;


@RestController
@RequestMapping("/api/review")
@CrossOrigin(origins = "http://localhost:5173")
public class ReviewFeedbackController {
	
	@Autowired
    private CustomerService customerService;

    @Autowired
    private RentalService rentalService;

    @Autowired
    private ReviewFeedbackService reviewFeedBackService;
    @Autowired
    private CarService carService;
 
	
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

    @PostMapping("/add/{rentalId}/{carId}/{customerId}")
    public ReviewFeedback addReviewFeedback(@PathVariable int rentalId,
                                            @PathVariable int carId,
                                            @PathVariable int customerId,
                                            @RequestBody ReviewFeedback reviewFeedback) throws InvalidIDException {

        // Get rental, car, and customer objects using services
        Rental rental = rentalService.getRentalById(rentalId); // already throws exception if not found
        Car car = carService.getById(carId); // no .orElseThrow() needed
        Customer customer = customerService.getCustomerById(customerId); // no .orElseThrow() needed

        // Validate: rental must belong to this customer
        if (rental.getCustomer().getId() != customer.getId()) {
            throw new InvalidIDException("Rental does not belong to the given customer.");
        }

        // Set associations in review
        reviewFeedback.setRental(rental);
        reviewFeedback.setCar(car);
        reviewFeedback.setCustomer(customer);

        return reviewFeedBackService.addReviewFeedback(reviewFeedback);
    }


	
}

