package com.api.carrental.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Service.DriverScheduleService;
import com.api.carrental.Service.DriverService;
import com.api.carrental.model.Driver;
import com.api.carrental.model.DriverSchedule;

@RestController
@RequestMapping("/api/driverSchedule")
public class DriverScheduleController {
	
	@Autowired
	private DriverService driverService;
	
	@Autowired
	private DriverScheduleService driverScheduleService;
	
	@PutMapping("/add/{driverId}")
	public DriverSchedule add(@PathVariable int driverId, 
			@RequestBody DriverSchedule driverSchedule) throws InvalidIDException{
		Driver driver = driverService.getById(driverId);
		driverSchedule.setDriver(driver);
	    return driverScheduleService.addOrUpdateByDriverId(driverId, driverSchedule);
	}
	
	
}
