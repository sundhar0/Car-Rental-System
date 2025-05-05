package com.api.carrental.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = {"http://localhost:5173/"})
@RequestMapping("/api/booking")
public class TestDriveController {
	@Autowired
	private TestDriveService testDriveService;

	//create the booking
	@PostMapping("/add/{carId}/{userId}")
	public Object addBooking(@PathVariable int carId,@PathVariable int userId,@RequestBody TestDrive testDrive) throws InvalidIDException {
		return testDriveService.addBooking(carId,userId,testDrive);
	}
	
	//showing all the booking
	@GetMapping("/showAll")
	public List<TestDrive> showAll() {
		return testDriveService.getAllBookings();
	}
	
	//get the booking details by the car id
	@GetMapping("/getByCarId/{carId}")
	public Object getByCarId(@PathVariable int carId) throws InvalidIDException {
		return testDriveService.getBookingsByCarId(carId);
	}
	
}
