package com.api.carrental.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.carrental.Exception.CarNotAvailable;
import com.api.carrental.Exception.DriverNotAvailable;
import com.api.carrental.Exception.InvalidDateException;
import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Repository.RentalWithDriverRepository;
import com.api.carrental.enums.CarSaleType;
import com.api.carrental.enums.CarStatus;
import com.api.carrental.enums.DriverAvailability;
import com.api.carrental.model.Car;
import com.api.carrental.model.Driver;
import com.api.carrental.model.RentalWithDriver;
import com.api.carrental.model.User;

@Service
public class RentalWithDriverService {
	
	@Autowired
	private CarService carService;

	@Autowired
	private AuthService authService;
	
	@Autowired
	private DriverService driverService;
	
	@Autowired
	private DriverScheduleService driverSchedueService;
	
	@Autowired
	private RentalWithDriverRepository rentalWithDriverRepository;
	
	public String rentWithDriver(RentalWithDriver rentalWithDriver,
			int driverId, int carId, int userId) throws DriverNotAvailable, InvalidIDException, CarNotAvailable {
		User user = authService.getById(userId);
		
		Car car = carService.getById(carId);
		
		Driver driver = driverService.getById(driverId);
		if(driver.getDriverAvailability() == DriverAvailability.UNAVAILABLE)
			throw new DriverNotAvailable("Driver was already get booked");
		
		if(car.getCarStatus() == CarStatus.PENDING
				|| car.getCarStatus() == CarStatus.REJECTED ||
				car.getCarSaleType() == CarSaleType.SELL)
			throw new CarNotAvailable("Sorry this car is not yet approved or Not available");
		
		driverSchedueService.getAvailableDriversOn(rentalWithDriver.getRentalStart(),
				rentalWithDriver.getRentalEnd());
		
		driver.setDriverAvailability(DriverAvailability.UNAVAILABLE);
		
		rentalWithDriver.setDriver(driver);
		rentalWithDriver.setUser(user);
		rentalWithDriver.setCar(car);
		
		rentalWithDriverRepository.save(rentalWithDriver);
		return "Rental was successfully initiated";
	}

	public List<RentalWithDriver> getAll() {
		return rentalWithDriverRepository.findAll();
	}

	public RentalWithDriver getById(int rentalId) throws InvalidIDException {
		Optional<RentalWithDriver> opt = rentalWithDriverRepository.findById(rentalId);
		if(opt.isEmpty())
			throw new InvalidIDException("Rental With Driver Id was wrong");
		return opt.get();
	}

	public RentalWithDriver getByDriverId(int driverId) throws InvalidIDException {
		Optional<RentalWithDriver> opt = rentalWithDriverRepository.findByDriverDriverId(driverId);
		if(opt.isEmpty())
			throw new InvalidIDException("Driver Not found with Id");
		return opt.get();
	}

	public RentalWithDriver getByUserId(int userId) throws InvalidIDException {
		Optional<RentalWithDriver> opt = rentalWithDriverRepository.findByDriverUserUserId(userId);
		if(opt.isEmpty())
			throw new InvalidIDException("User not found");
		return opt.get();
	}

	public List<RentalWithDriver> getByDriverName(String name) throws DriverNotAvailable {
		List<RentalWithDriver> list = rentalWithDriverRepository.findAllByDriverName(name);
		if(list == null)
			throw new DriverNotAvailable("Driver not found");
		return list;
	}

	public List<RentalWithDriver> getByDate(LocalDate dateFrom, LocalDate dateTo) throws InvalidDateException {
		List<RentalWithDriver> list = rentalWithDriverRepository
				.findByRentalStartLessThanEqualAndRentalEndGreaterThanEqual(dateFrom,dateTo);
		if(list == null)
			throw new InvalidDateException("Date not found");
		return list;
		
	}

	public void deleteRentalWithDriver(int rentalId) throws InvalidIDException {
        Optional<RentalWithDriver> optionalRental = rentalWithDriverRepository.findById(rentalId);
        if (optionalRental.isEmpty()) {
            throw new InvalidIDException("Rental With Driver not found");
        }
        rentalWithDriverRepository.deleteById(rentalId);
    }
}
