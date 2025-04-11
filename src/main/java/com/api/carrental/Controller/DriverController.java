package com.api.carrental.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.carrental.Exception.LicenseNoAlreadyAssigned;
import com.api.carrental.Service.DriverService;
import com.api.carrental.model.Driver;

@RestController
@RequestMapping("/api/driver")
public class DriverController {
	
	@Autowired
	private DriverService driverService;
	
	@PostMapping("/add")
	public Driver add(@RequestBody Driver driver) throws LicenseNoAlreadyAssigned {
		return driverService.add(driver);
	}
}
