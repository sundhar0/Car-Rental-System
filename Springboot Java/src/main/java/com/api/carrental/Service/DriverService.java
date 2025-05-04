package com.api.carrental.Service;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.carrental.Exception.DriverNotAvailable;
import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Exception.InvalidUserNameException;
import com.api.carrental.Exception.LicenseNoAlreadyAssigned;
import com.api.carrental.Repository.DriverRepository;
import com.api.carrental.enums.DriverAvailability;
import com.api.carrental.model.Driver;
import com.api.carrental.model.User;

@Service
public class DriverService {
	
	@Autowired
	private DriverRepository driverRepository;
	
	
	@Autowired
	private AuthService authService;
	
	Logger logger=LoggerFactory.getLogger("DriverService");
	//adding the driver using userId
	//getting the details of the driver and the user id
	//fetching the details of the user and setting it into the driver
	//fetching the details of the driver by the driving licence number for checking the driver is already a driver or not
	//setting the driver availability into available
	//saving the details of the driver as available
	public Driver add(Driver driver, int userId) throws LicenseNoAlreadyAssigned, InvalidIDException {
		User user = authService.getById(userId);
		driver.setUser(user);
		Driver driver1 = driverRepository.findByLicenseNo(driver.getLicenseNo());
		if(driver1 != null)
			throw new LicenseNoAlreadyAssigned("The license no already assigned to other driver");
		driver.setDriverAvailability(DriverAvailability.AVAILABLE);
		logger.info("Driver Added Successfully");
		return driverRepository.save(driver);
	}
	//fetching the details of the driver by name
	//getting driver name and checking it in the db
	public Driver getByName(String driverUsername) throws InvalidUserNameException {
		Driver driver1 = driverRepository.findByUserUsername(driverUsername);
		if(driver1 == null)
			throw new InvalidUserNameException("Driver not found by this username");
		return driver1;
	}
	//fetching the details of the driver by his id
	//fetch it and return
	public Driver getById(int driverId) throws InvalidIDException {
		Optional<Driver> driver = driverRepository.findById(driverId);
		if(driver.isEmpty())
			throw new InvalidIDException("Driver not found with this Id");
		return driver.get();
	}
	//fetching the drivers by their availability
	//And returning the driver details
	public List<Driver> getByAvailable() throws DriverNotAvailable {
		List<Driver> drivers = driverRepository
				.findByDriverAvailability(DriverAvailability.AVAILABLE);
		if(drivers == null)
			throw new DriverNotAvailable("Drivers not available");
		return drivers;
	}
	//updating the drivers availability
	//fetching the details from API and check it by their id
	//update the availability
	public Driver updateAvailablility(int driverId, String availability) throws DriverNotAvailable {
		Driver existingDriver = driverRepository.findByDriverId(driverId);
		if(existingDriver == null)
			throw new DriverNotAvailable("driver not found");
		existingDriver.setDriverAvailability(DriverAvailability.valueOf(availability.toUpperCase()));
		logger.info("Driver with "+driverId+" updated successfully");
		return driverRepository.save(existingDriver);
//		return "Driver Availability updated";
	}
	//deleting the driver
	//fetching the driver id
	//deleting the driver by his id
	public void deleteDriver(int driverId) throws InvalidIDException {
        Optional<Driver> optionalDriver = driverRepository.findById(driverId);
        if (optionalDriver.isEmpty()) {
            throw new InvalidIDException("Driver not found");
        }
        logger.info("Driver with "+driverId+" is deleted Successfully");
        driverRepository.deleteById(driverId);
    }
	
}
