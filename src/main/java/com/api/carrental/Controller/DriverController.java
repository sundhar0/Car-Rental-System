package com.api.carrental.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.carrental.Exception.DriverNotAvailable;
import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Exception.InvalidUserNameException;
import com.api.carrental.Exception.LicenseNoAlreadyAssigned;
import com.api.carrental.Service.AuthService;
import com.api.carrental.Service.DriverService;
import com.api.carrental.model.Driver;
import com.api.carrental.model.User;

@RestController
@RequestMapping("/api/driver")
public class DriverController {
	
	@Autowired
	private DriverService driverService;
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/add/{userId}")
	public String add(@PathVariable int userId, @RequestBody Driver driver) throws LicenseNoAlreadyAssigned, InvalidIDException {
		User user = authService.getById(userId);
		driver.setUser(user);
		driverService.add(driver);
		return "Driver add sucessfully";
	}
	
	@GetMapping("/available")
	public List<Driver> getByAvailable() throws DriverNotAvailable {
		return driverService.getByAvailable();
	}
	
	@GetMapping("/name")
	public Driver getByName(@RequestParam String driverUsername) throws InvalidUserNameException {
		return driverService.getByName(driverUsername);
	}
	
	@GetMapping("/getById/{driverId}")
	public Driver getById(@PathVariable int driverId) throws InvalidIDException {
		return driverService.getById(driverId);
	}
	
	@GetMapping("/getByAvailableDate")
	public ResponseEntity<List<Driver>> getByAvailableDate(@RequestParam LocalDate date) {
		return ResponseEntity.ok(driverService.getAvailableDriversOn(date));
	}
}
