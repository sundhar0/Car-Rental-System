package com.api.carrental.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.carrental.Exception.CarNotAvailable;
import com.api.carrental.Exception.DriverNotAvailable;
import com.api.carrental.Exception.InvalidDateException;
import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Repository.RentalWithDriverRepository;
import com.api.carrental.enums.CarSaleType;
import com.api.carrental.enums.CarStatus;
import com.api.carrental.enums.DriverAvailability;
import com.api.carrental.enums.RideStatus;
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
    Logger logger=LoggerFactory.getLogger("RentalWithDriverService");
    //assigning the rental with driver
    //getting the details and add the cars with the rental by its driver id,car id and user id
    //fetch the details by its id
    //set it into table
    //checking the driver availability on the dates
    //add the driver in the rental car
    public String rentWithDriver(RentalWithDriver rentalWithDriver,
                                 int driverId, int carId, int userId) throws DriverNotAvailable, InvalidIDException, CarNotAvailable {
        User user = authService.getById(userId);

        Car car = carService.getById(carId);

        Driver driver = driverService.getById(driverId);
        if (driver.getDriverAvailability() == DriverAvailability.UNAVAILABLE)
            throw new DriverNotAvailable("Driver is already booked");

        if (car.getStatus() == CarStatus.PENDING || car.getStatus() == CarStatus.REJECTED ||
                car.getCarSaleType() == CarSaleType.SELL)
            throw new CarNotAvailable("Sorry, this car is not available");



        driver.setDriverAvailability(DriverAvailability.UNAVAILABLE);

        rentalWithDriver.setDriver(driver);
        rentalWithDriver.setUser(user);
        rentalWithDriver.setCar(car);
        rentalWithDriver.setRideStatus(RideStatus.WAITING_FOR_DRIVER);  // Initial ride status

        rentalWithDriverRepository.save(rentalWithDriver);
        logger.info("rent with driver added..");
        return "Rental was successfully initiated";
    }

    //get the all the drivers with assigned with the cars
    public List<RentalWithDriver> getAll() {
        return rentalWithDriverRepository.findAll();
    }
    //get the details by the id
    public RentalWithDriver getById(int rentalId) throws InvalidIDException {
        Optional<RentalWithDriver> opt = rentalWithDriverRepository.findById(rentalId);
        if (opt.isEmpty())
            throw new InvalidIDException("Rental With Driver Id is invalid");
        return opt.get();
    }
    //get the details of the rental by the driver id
    public RentalWithDriver getByDriverId(int driverId) throws InvalidIDException {
        Optional<RentalWithDriver> opt = rentalWithDriverRepository.findByDriverDriverId(driverId);
        if (opt.isEmpty())
            throw new InvalidIDException("Driver not found with Id");
        return opt.get();
    }
    //get the details by the user id
    public RentalWithDriver getByUserId(int userId) throws InvalidIDException {
        Optional<RentalWithDriver> opt = rentalWithDriverRepository.findByDriverUserUserId(userId);
        if (opt.isEmpty())
            throw new InvalidIDException("User not found");
        return opt.get();
    }
    //get the details by the user name
    public List<RentalWithDriver> getByDriverName(String name) throws DriverNotAvailable {
        List<RentalWithDriver> list = rentalWithDriverRepository.findAllByDriverName(name);
        if (list == null)
            throw new DriverNotAvailable("Driver not found");
        return list;
    }
    //get the details by the date
    public List<RentalWithDriver> getByDate(LocalDate dateFrom, LocalDate dateTo) throws InvalidDateException {
        List<RentalWithDriver> list = rentalWithDriverRepository
                .findByRentalStartLessThanEqualAndRentalEndGreaterThanEqual(dateFrom, dateTo);
        if (list == null)
            throw new InvalidDateException("Date range not found");
        return list;
    }
    //delete the details by the rental id
    public void deleteRentalWithDriver(int rentalId) throws InvalidIDException {
        Optional<RentalWithDriver> optionalRental = rentalWithDriverRepository.findById(rentalId);
        if (optionalRental.isEmpty()) {
            throw new InvalidIDException("Rental With Driver not found");
        }
        logger.info("Deleted Rental with driver...");
        rentalWithDriverRepository.deleteById(rentalId);
    }
    //updating the status of the rental / updating the status
    public void updateRideStatus(int rentalId, RideStatus rideStatus) throws InvalidIDException {
        RentalWithDriver rental = getById(rentalId);
        rental.setRideStatus(rideStatus);
        logger.info("Updated rental with driver...");
        rentalWithDriverRepository.save(rental);
    }

    public Page<RentalWithDriver> getRidesForDriver(Integer driverId, Pageable pageable) {
        return rentalWithDriverRepository.findByDriverDriverId(driverId, pageable);
    }
}
