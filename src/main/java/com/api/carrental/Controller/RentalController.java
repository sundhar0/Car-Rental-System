package com.api.carrental.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.carrental.Service.AuthService;
import com.api.carrental.Service.CarService;
import com.api.carrental.Service.RentalService;
import com.api.carrental.model.*;

@RestController
@RequestMapping("/api/rent")
public class RentalController {
	
	@Autowired
	private RentalService rentalService;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private CarService carService;
	
	@PostMapping("/add/{carId}")
	public Rental add(@PathVariable int carId, Principal principal, 
			@RequestBody Rental rental) {
		User user = authService.getByUsername(principal.getName());
		Car car = carService.getById(carId);
		rental.setCar(car);
		rental.setUser(user);
		return rentalService.add(rental);
	}
}
