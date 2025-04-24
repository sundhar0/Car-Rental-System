package com.api.carrental.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Service.AuthService;
import com.api.carrental.Service.CarService;
import com.api.carrental.Service.CustomerService;
import com.api.carrental.model.Car;
import com.api.carrental.model.Customer;


@RestController
@RequestMapping("/api/car")
public class CarController {
	
	@Autowired
	private CarService carService;
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/add/{ownId}")
	
	public Car add(@PathVariable int ownId, @RequestBody Car car) {
		Optional<Customer> customer = customerService.getById(ownId);
		car.setCustomer(customer.get());
		return carService.add(car);
	}
	
	@GetMapping("/getAll")
	public List<Car> getAll(){
		return carService.getAll();
	}
	
	@GetMapping("/getReview/{cId}")
	public Object SeeReview(@PathVariable int cId) {
		return carService.getReview(cId);
	}
	
	
}
