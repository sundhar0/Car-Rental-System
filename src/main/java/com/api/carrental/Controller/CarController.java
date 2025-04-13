package com.api.carrental.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
<<<<<<< HEAD

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Service.AuthService;
=======
>>>>>>> 477a9c7d1f831c0507906241b73e6083a73153db
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
<<<<<<< HEAD
	public Car add(@PathVariable int ownId, @RequestBody Car car) throws InvalidIDException {
		User user = authService.getById(ownId);
		car.setCarOwner(user);
=======
	public Car add(@PathVariable int ownId, @RequestBody Car car) {
		Optional<Customer> customer = customerService.getById(ownId);
		car.setCustomer(customer.get());
>>>>>>> 477a9c7d1f831c0507906241b73e6083a73153db
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
