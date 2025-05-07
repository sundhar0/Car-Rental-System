package com.api.carrental.Controller;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.carrental.Exception.DriverNotAvailable;
import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Exception.InvalidUserNameException;
import com.api.carrental.Exception.LicenseNoAlreadyAssigned;
import com.api.carrental.Service.DriverService;
import com.api.carrental.dto.DriverDto;
import com.api.carrental.model.Driver;

@RestController
@CrossOrigin(origins = {"http://localhost:5173/"})
@RequestMapping("/api/driver")
public class DriverController {
	
	@Autowired
	private DriverService driverService;
	
	@Autowired
	private DriverDto dto;
	
	@PostMapping("/add")
	public Driver add(@RequestBody Driver driver) throws LicenseNoAlreadyAssigned, InvalidIDException, InvalidUserNameException {
		
		return driverService.add(driver);
		
	}
	
	@GetMapping("/getAll")
	public DriverDto getByAvailable(@RequestParam int page, @RequestParam int size) throws DriverNotAvailable {
		Pageable pageable = PageRequest.of(page, size); 
		Page<Driver> driverP = driverService.getAll(pageable); 
		dto.setList(driverP.getContent());
		dto.setCurrentPage(page);
		dto.setSize(size);
		dto.setTotalElements((int) driverP.getTotalElements());
		dto.setTotalPages(driverP.getTotalPages());
		return dto; 
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
	
	@DeleteMapping("delete/{driverId}")
    public ResponseEntity<String> deleteDriver(@PathVariable int driverId) throws InvalidIDException {
       
            driverService.deleteDriver(driverId);
            return ResponseEntity.ok("Driver deleted successfully");
        
    }
	
	@PutMapping("update/{driverId}")
	public ResponseEntity<String> updateDriverExperienceAndCharge(@PathVariable int driverId,
	                                                              @RequestParam int experienceYears,
	                                                              @RequestParam double perDayCharge) throws InvalidIDException {
	    driverService.updateDriverExperienceAndCharge(driverId, experienceYears, perDayCharge);
	    return ResponseEntity.ok("Driver details updated successfully");
	}
	
	@PostMapping("/image/upload/{pid}")
	public Driver uploadImage(@PathVariable int pid, 
							@RequestParam MultipartFile file) throws IOException, InvalidIDException {
		
		return driverService.uploadImage(file,pid);
	}
	
}
