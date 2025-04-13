package com.api.carrental.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.carrental.Exception.DriverNotAvailable;
import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Exception.InvalidUserNameException;
import com.api.carrental.Exception.LicenseNoAlreadyAssigned;
import com.api.carrental.Repository.DriverRepository;
import com.api.carrental.Repository.DriverScheduleRepository;
import com.api.carrental.enums.DriverAvailability;
import com.api.carrental.model.Driver;
import com.api.carrental.model.DriverSchedule;

@Service
public class DriverService {
	
	@Autowired
	private DriverRepository driverRepository;
	
	@Autowired
	private DriverScheduleRepository scheduleRepository;
	
	public Driver add(Driver driver) throws LicenseNoAlreadyAssigned {
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

	public List<Driver> getAvailableDriversOn(LocalDate date) {
	    List<DriverSchedule> schedules = scheduleRepository
	        .findByAvailableFromLessThanEqualAndAvailableToGreaterThanEqual(date, date);
	    return schedules.stream()
	        .map(ds->ds.getDriver())
	        .distinct().toList();
	}
	
	
}
