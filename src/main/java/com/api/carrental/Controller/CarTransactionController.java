package com.api.carrental.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.carrental.Service.AuthService;
import com.api.carrental.Service.CarService;
import com.api.carrental.Service.CarTransactionService;
import com.api.carrental.model.Car;
import com.api.carrental.model.CarTransaction;
import com.api.carrental.model.User;

@RestController
@RequestMapping("/api/ct")
public class CarTransactionController {
	
	@Autowired
	private CarService carService;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private CarTransactionService ctService;
	
	@PostMapping("/buy/{sellerId}/{carId}/{buyerId}")
	public CarTransaction buy(@RequestBody CarTransaction ct,
			@PathVariable int carId, @PathVariable int sellerId, @PathVariable int buyerId) {
		
		Car car = carService.getById(carId);
		User seller = authService.getById(sellerId);
		User buyer = authService.getById(buyerId);
		
		ct.setCar(car);
		ct.setSeller(seller);
		ct.setBuyer(buyer);
		
		ct = ctService.buy(ct);
		
		return ct;
	}
}
