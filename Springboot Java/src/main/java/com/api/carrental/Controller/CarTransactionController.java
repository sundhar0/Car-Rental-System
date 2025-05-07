package com.api.carrental.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Service.CarService;
import com.api.carrental.Service.CarTransactionService;
import com.api.carrental.Service.CustomerService;
import com.api.carrental.enums.CarSaleType;
import com.api.carrental.enums.CarStatus;
import com.api.carrental.model.Car;
import com.api.carrental.model.CarTransaction;
import com.api.carrental.model.Customer;

@RestController
@RequestMapping("/api/ct")
public class CarTransactionController {
	
	@Autowired
	private CarService carService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CarTransactionService ctService;
	
	@PostMapping("/buy/{sellerId}/{carId}/{buyerId}")
	public CarTransaction buy(@RequestBody CarTransaction ct,
			@PathVariable int carId, @PathVariable Long sellerId, @PathVariable Long buyerId) throws InvalidIDException {

		
		Car car = carService.getById(carId);
		Customer seller = customerService.getSingleCustomer(sellerId);
		Customer buyer = customerService.getSingleCustomer(buyerId);
		car.setCarSaleType(CarSaleType.SOLD);
		car.setStatus(CarStatus.NOT_AVAILABLE);
		ct.setCar(car);
		ct.setSeller(seller);
		ct.setBuyer(buyer);
		ct = ctService.buy(ct);
		
		return ct;
	}
}
