package com.api.carrental.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.carrental.Exception.CarNotAvailable;
import com.api.carrental.Exception.DriverNotAvailable;
import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Service.RentalWithDriverService;
import com.api.carrental.model.Driver;
import com.api.carrental.model.RentalWithDriver;

@RestController
@RequestMapping("/api/rentWithDriver")
public class RentalWithDriverController {
	
	
	
	@Autowired
	private RentalWithDriverService rentalWithDriverService;
	
	@PostMapping("/rent")
	public String addRent(@RequestBody RentalWithDriver rentalWithDriver,
			@RequestParam int driverId, @RequestParam int userId,
			@RequestParam int carId) throws InvalidIDException, DriverNotAvailable, CarNotAvailable {
		
		
		return rentalWithDriverService
				.rentWithDriver(rentalWithDriver,driverId,carId,userId);
	}
	
	@GetMapping("/getAll")
	public List<RentalWithDriver> getAll() {
		return rentalWithDriverService.getAll();
	}
	
	@GetMapping("/getById/{rentalId}")
	public RentalWithDriver getById(@PathVariable int rentalId) throws InvalidIDException {
		return rentalWithDriverService.getById(rentalId);
	}
	
	@GetMapping("/getById/{driverId}")
	public RentalWithDriver getByDriverId(@PathVariable int driverId) throws InvalidIDException {
		return rentalWithDriverService.getByDriverId(driverId);
	}
	
	@GetMapping("/getByUserId/{userId}")
	public RentalWithDriver getByUserId(@PathVariable int userId) throws InvalidIDException {
		return rentalWithDriverService.getByUserId(userId);
	}
}
