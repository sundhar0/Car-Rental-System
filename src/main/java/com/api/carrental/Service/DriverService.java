package com.api.carrental.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.carrental.Repository.DriverRepository;
import com.api.carrental.enums.DriverAvailability;
import com.api.carrental.model.Driver;

@Service
public class DriverService {
	
	@Autowired
	private DriverRepository driverRepository;
	
	public Driver add(Driver driver) {
		driver.setDriverAvailability(DriverAvailability.AVAILABLE);
		return driverRepository.save(driver);
	}
}
