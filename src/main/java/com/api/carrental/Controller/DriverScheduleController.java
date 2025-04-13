package com.api.carrental.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	
	@PutMapping("/addOrUpdate/{driverId}")
	public DriverSchedule add(@PathVariable int driverId, 
			@RequestBody DriverSchedule driverSchedule) throws InvalidIDException{
		Driver driver = driverService.getById(driverId);
		driverSchedule.setDriver(driver);
	    return driverScheduleService.addOrUpdateByDriverId(driverId, driverSchedule);
	}
	
	@GetMapping("/getByAvailableDate")
	public ResponseEntity<List<Driver>> getByAvailableDate(@RequestParam LocalDate dateFrom,
			@RequestParam LocalDate dateTo) throws DriverNotAvailable {
		return ResponseEntity.ok(driverScheduleService.
				getAvailableDriversOn(dateFrom, dateTo));
	}
	
	@GetMapping("/getAll")
	public List<DriverSchedule> getAll(){
		return driverScheduleService.getAll();
	}
	
	@GetMapping("/getById/{driverScheId}")
	public DriverSchedule getById(@PathVariable int driverScheId) throws InvalidIDException {
		return driverScheduleService.getById(driverScheId);
	}
	
	@GetMapping("/getByDriverUsername")
	public DriverSchedule getByDriverUsername(@RequestParam String username) {
		return driverScheduleService.getByDriverUsername(username);
	}
	
}
