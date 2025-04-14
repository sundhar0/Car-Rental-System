package com.api.carrental.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.carrental.Exception.DriverNotAvailable;
import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Exception.InvalidUserNameException;
import com.api.carrental.Exception.LicenseNoAlreadyAssigned;
import com.api.carrental.Service.DriverService;
import com.api.carrental.model.Driver;

@RestController
@RequestMapping("/api/driver")
public class DriverController {
	
	@Autowired
	private DriverService driverService;
	
	
	
	@PostMapping("/add/{userId}")
	public String add(@PathVariable int userId, @RequestBody Driver driver) throws LicenseNoAlreadyAssigned, InvalidIDException {
		
		driverService.add(driver,userId);
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
	
	@PutMapping("/updateAvailablility/{driverId}/{availablility}")
	public Driver updateAvailablility(@PathVariable int driverId, 
			@PathVariable String availablility) throws DriverNotAvailable {
		return driverService.updateAvailablility(driverId,availablility);
	}
	
	@DeleteMapping("/{driverId}")
    public ResponseEntity<String> deleteDriver(@PathVariable int driverId) throws InvalidIDException {
       
            driverService.deleteDriver(driverId);
            return ResponseEntity.ok("Driver deleted successfully");
        
    }
	
}
