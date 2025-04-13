package com.api.carrental.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.carrental.Exception.DriverNotAvailable;
import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Service.AuthService;
import com.api.carrental.Service.DriverService;
import com.api.carrental.Service.RentalWithDriverService;
import com.api.carrental.enums.DriverAvailability;
import com.api.carrental.model.Driver;
import com.api.carrental.model.RentalWithDriver;
import com.api.carrental.model.User;

@RestController
@RequestMapping("/api/rentWithDriver")
public class RentalWithDriverController {
	

	@Autowired
	private AuthService authService;
	
	@Autowired
	private DriverService driverService;
	
	private RentalWithDriverService rentalWithDriverService;
	
	@PostMapping("/rent")
	public String addRent(@RequestBody RentalWithDriver rentalWithDriver,
			@RequestParam int driverId, @RequestParam int userId,
			@RequestParam int bookingId) throws InvalidIDException, DriverNotAvailable {
		User user = authService.getById(userId);
		Driver driver = driverService.getById(driverId);
		if(driver.getDriverAvailability() == DriverAvailability.UNAVAILABLE)
			throw new DriverNotAvailable("Driver was already get booked");
		
	}
	
}
