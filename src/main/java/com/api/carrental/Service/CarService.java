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
import com.api.carrental.model.ReviewFeedback;
import com.api.carrental.enums.CarSaleType;
import com.api.carrental.enums.CarStatus;

@Service
public class CarService {
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private CarApprovalRepository carApprovalRepository;
	@Autowired
	private ReviewFeedbackService reviewFeedbackService;
	
	
	public Car add(Car car) {
		//this method will store the car status will defaultly in pending
		// after the manager given the approval it changes to approved
		car.setCarStatus(CarStatus.PENDING);
		//it will save the cars in the car table
	    return carRepository.save(car);
	}

	public Car getById(int carId) throws InvalidIDException {
		Optional<Car> opt = carRepository.findById(carId);
		if(opt.isEmpty())
			throw new InvalidIDException("Car was not found");
		return opt.get();
	}

	public List<Car> getAll() {
		
		List<CarApproval> approvedApprovals = carApprovalRepository.findByApprovedTrue();
	    return approvedApprovals.stream()
	    						.filter(ca->ca.getCar().getCarSaleType() == CarSaleType.RENT)
	    						.map(ca->ca.getCar())
	                            .toList();
	}

	

	
	
}
