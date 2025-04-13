package com.api.carrental.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.carrental.Service.BuyerService;
import com.api.carrental.model.Car;

@RestController
@RequestMapping("/api/buyer")
public class BuyerController {
	@Autowired
	private BuyerService buyerService;

	@GetMapping("/getAll")
	public List<Car> getAll(){
		return buyerService.getAll();
	}
	
	@GetMapping("/getByModel/{model}")
	public Object getByModel(@PathVariable String model) {
		return buyerService.getByModel(model);
	}
	
	@GetMapping("/showbyYear/{year}")
	public Object getByYear(@PathVariable String year) {
		return buyerService.getByYear(year);
	}
	
	@GetMapping("/showByFuelType/{ft}")
	public Object getByFueltype(@PathVariable String ft) {
		return buyerService.getByFuelType(ft); 
	}
	
	@GetMapping("showByRate/{amount}")
	public Object getByPrice(@PathVariable double amount) {
		return buyerService.getByPrice(amount);
	}
	
}
