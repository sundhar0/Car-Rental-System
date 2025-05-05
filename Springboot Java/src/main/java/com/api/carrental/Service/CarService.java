package com.api.carrental.Service;

import java.util.List;


import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Repository.CarApprovalRepository;
import com.api.carrental.Repository.CarRepository;
import com.api.carrental.model.Car;
import com.api.carrental.model.CarApproval;
import com.api.carrental.model.Customer;
//import com.api.carrental.model.ReviewFeedback;
import com.api.carrental.enums.CarSaleType;
import com.api.carrental.enums.CarStatus;

@Service
public class CarService {
	
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private CarApprovalRepository carApprovalRepository;
//	@Autowired
//	private ReviewFeedbackService reviewFeedbackService;
	@Autowired
	private CustomerService customerService;
	
	
	public Car add(Car car) {
		//this method will store the car status will defaultly in pending
		// after the manager given the approval it changes to approved
		car.setStatus(CarStatus.PENDING);
		//it will save the cars in the car table
	    return carRepository.save(car);
	}

	public Car getById(int carId) throws InvalidIDException {
		// this will be helpful to find the cars in a particular car id
		Optional<Car> opt = carRepository.findById(carId);
		if(opt.get()==null)
			throw new InvalidIDException("Given Id is Invalid....");

		return opt.get();
	}

	public List<Car> getAll() {
		
		List<CarApproval> approvedApprovals = carApprovalRepository.findByApprovedTrue();
	    return approvedApprovals.stream()
	    						.filter(ca->ca.getCar().getCarSaleType() == CarSaleType.SELL)
	    						.map(ca->ca.getCar())
	                            .toList();
	}


//	public Object getReview(Long cId) throws InvalidIDException {
//		//this will be used to show the feedback about the customer
//		//after getting the customer id we are checking the details about the cutomer in the reviewfeedback table
//		List<ReviewFeedback> list=reviewFeedbackService.getByReview(cId);
//		if(list.isEmpty())
//			throw new InvalidIDException("Given Customer Id is Invalid...");
//		return list;
//	}

	public Object getHistory(Long cId) throws InvalidIDException {
		//it will get the history by customer id
		Customer customer=customerService.getSingleCustomer(cId);
		if(customer==null)
			throw new InvalidIDException("Given Customer Id is Inavlid...");
		return carRepository.findByCustomerId(cId);

	}

	public void DeleteCar(int cId) throws InvalidIDException {
		Optional<Car> optional=carRepository.findById(cId);
		if(optional==null)
			throw new InvalidIDException("Given Car Id is Invalid!!");
		carRepository.deleteById(cId);
		
	}

	
	
}
