package com.api.carrental.Service;


import java.util.List;
import java.util.Optional;

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
	
	public Driver add(Driver driver, int userId) throws LicenseNoAlreadyAssigned, InvalidIDException {
		User user = authService.getById(userId);
		driver.setUser(user);
		Driver driver1 = driverRepository.findByLicenseNo(driver.getLicenseNo());
		if(driver1 != null)
			throw new LicenseNoAlreadyAssigned("The license no already assigned to other driver");
		driver.setDriverAvailability(DriverAvailability.AVAILABLE);
		return driverRepository.save(driver);
	}

	public Driver getByName(String driverUsername) throws InvalidUserNameException {
		Driver driver1 = driverRepository.findByUserUsername(driverUsername);
		if(driver1 == null)
			throw new InvalidUserNameException("Driver not found by this username");
		return driver1;
	}

	public Driver getById(int driverId) throws InvalidIDException {
		Optional<Driver> driver = driverRepository.findById(driverId);
		if(driver.isEmpty())
			throw new InvalidIDException("Driver not found with this Id");
		return driver.get();
	}

	public List<Driver> getByAvailable() throws DriverNotAvailable {
		List<Driver> drivers = driverRepository
				.findByDriverAvailability(DriverAvailability.AVAILABLE);
		if(drivers == null)
			throw new DriverNotAvailable("Drivers not available");
		return drivers;
	}

	public Driver updateAvailablility(int driverId, String availability) throws DriverNotAvailable {
		Driver existingDriver = driverRepository.findByDriverId(driverId);
		if(existingDriver == null)
			throw new DriverNotAvailable("driver not found");
		existingDriver.setDriverAvailability(DriverAvailability.valueOf(availability.toUpperCase()));
		return driverRepository.save(existingDriver);
//		return "Driver Availability updated";
	}

	public void deleteDriver(int driverId) throws InvalidIDException {
        Optional<Driver> optionalDriver = driverRepository.findById(driverId);
        if (optionalDriver.isEmpty()) {
            throw new InvalidIDException("Driver not found");
        }
        driverRepository.deleteById(driverId);
    }
	
}
