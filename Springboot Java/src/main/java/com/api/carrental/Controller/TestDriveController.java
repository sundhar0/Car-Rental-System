package com.api.carrental.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Service.TestDriveService;
import com.api.carrental.model.TestDrive;

@RestController
@RequestMapping("/api/booking")
public class TestDriveController {
	@Autowired
	private TestDriveService testDriveService;

	//create the booking
	@PostMapping("/add/{carId}/{cusId}")
	public Object addBooking(@PathVariable int carId,@PathVariable Long cusId,@RequestBody TestDrive testDrive) throws InvalidIDException {
		return testDriveService.addBooking(carId,cusId,testDrive);
	}
	
	//showing all the booking
	@GetMapping("/showAll")
	public List<TestDrive> showAll() {
		return testDriveService.getAllBooking();
	}
	
	//get the booking details by the car id
	@GetMapping("/getByCarId/{carId}")
	public Object getByCarId(@PathVariable int carId) throws InvalidIDException {
		return testDriveService.getByCarId(carId);
	}
	//get the booking details from customer id
	@GetMapping("/showByCustomer/{cusId}")
	public Object GetByCusId(@PathVariable Long cusId) throws InvalidIDException {
		return testDriveService.getByCustomerId(cusId);
	}
}
