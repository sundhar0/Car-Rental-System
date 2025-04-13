package com.api.carrental.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.carrental.Repository.RentalWithDriverRepository;
import com.api.carrental.model.RentalWithDriver;

@Service
public class RentalWithDriverService {
	
	@Autowired
	private RentalWithDriverRepository rentalWithDriverRepository;
	
	public String rentWithDriver(RentalWithDriver rentalwithDriver) {
		rentalWithDriverRepository.save(rentalwithDriver);
		return "Rental was successfully initiated";
	}
}
