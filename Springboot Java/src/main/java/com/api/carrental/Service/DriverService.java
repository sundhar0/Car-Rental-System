package com.api.carrental.Service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	public Driver add(Driver driver) throws LicenseNoAlreadyAssigned, InvalidIDException, InvalidUserNameException {
		User user1 = driver.getUser();
		User user = authService.getByUsername(user1.getUsername());
		if(user != null) 
			throw new InvalidUserNameException("Username exist");
		user1.setRole("DRIVER");
		user1 = authService.add(user1);
		driver.setUser(user1);
		Driver driver1 = driverRepository.findByLicenseNo(driver.getLicenseNo());
		if(driver1 != null)
			throw new LicenseNoAlreadyAssigned("The license no already assigned to other driver");
		
		driver.setDriverAvailability(DriverAvailability.UNAVAILABLE);
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
	//fetching all the drivers
	public Page<Driver> getAll(Pageable pageable) throws DriverNotAvailable {
		Page<Driver> drivers = driverRepository.findAll(pageable);
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
	//updating the drivers details
	//get the experience years and amount per day
	//getting the details of the using driver id
	//setting the drivers experience and salary
	public void updateDriverExperienceAndCharge(int driverId, int experienceYears, double perDayCharge) throws InvalidIDException {
	    Optional<Driver> optionalDriver = driverRepository.findById(driverId);
	    if (optionalDriver.isEmpty()) {
	        throw new InvalidIDException("Driver not found");
	    }
	    Driver driver = optionalDriver.get();
	    driver.setExperienceYears(experienceYears);
	    driver.setPerDayCharge(perDayCharge);
	    driverRepository.save(driver);
	}

	public Driver uploadImage(MultipartFile file,int pid) throws IOException, InvalidIDException {
		/*check if pid isvalid */
		Driver driver = driverRepository.findById(pid)
				.orElseThrow(()->new InvalidIDException("Invalid PID given.."));
		
		List<String> allowedExtensions = Arrays.asList("png","jpg","jpeg","gif","svg"); 
		String originalFileName = file.getOriginalFilename(); 
		System.out.println(originalFileName);
		String extension= originalFileName.split("\\.")[1];
		/*Check weather extension is allowed or not */
		if( !(allowedExtensions.contains(extension))) {
			throw new RuntimeException("Image Type Invalid");
		}		
		String uploadPath= "D:\\";
		
		/*Create directory *///Check if directory is present else create it
		Files.createDirectories(Paths.get(uploadPath));
		/*Define full path with folder and image name */
		Path path = Paths.get(uploadPath + "\\" +originalFileName); 
		/*Copy the image into uploads path */
		Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		/*Save this path in Db */
		driver.setProfilePic(path.toString());
		return driverRepository.save(driver);
	}
	
}
