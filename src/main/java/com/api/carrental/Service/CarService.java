package com.api.carrental.Service;

import java.util.List;


import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.carrental.Repository.CarApprovalRepository;
import com.api.carrental.Repository.CarRepository;
import com.api.carrental.model.Car;
import com.api.carrental.model.CarApproval;
import com.api.carrental.enums.CarSaleType;
import com.api.carrental.enums.CarStatus;

@Service
public class CarService {
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private CarApprovalRepository carApprovalRepository;
	
	
	
	public Car add(Car car) {
		car.setCarStatus(CarStatus.PENDING);
	    return carRepository.save(car);
	}

	public Car getById(int carId) {
		Optional<Car> opt = carRepository.findById(carId);
		return opt.get();
	}

	public List<Car> getAll() {
		List<CarApproval> approvedApprovals = carApprovalRepository.findByApprovedTrue();
	    return approvedApprovals.stream()
	    						.filter(ca->ca.getCar().getCarSaleType() == CarSaleType.SELL)
	    						.map(ca->ca.getCar())
	                            .toList();
	}
	
	
}
