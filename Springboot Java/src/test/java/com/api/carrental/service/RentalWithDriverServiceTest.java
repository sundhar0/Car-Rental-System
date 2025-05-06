package com.api.carrental.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.api.carrental.Exception.CarNotAvailable;
import com.api.carrental.Exception.DriverNotAvailable;
import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Repository.RentalWithDriverRepository;
import com.api.carrental.Service.AuthService;
import com.api.carrental.Service.RentalWithDriverService;
import com.api.carrental.enums.DriverAvailability;
import com.api.carrental.model.Car;
import com.api.carrental.model.Driver;
import com.api.carrental.model.RentalWithDriver;
import com.api.carrental.model.User;

@ExtendWith(MockitoExtension.class)
public class RentalWithDriverServiceTest {
	@Mock
	private AuthService authService;


	@Mock
    private RentalWithDriverRepository rentalWithDriverRepository;

    @InjectMocks
    private RentalWithDriverService rentalWithDriverService;

    private RentalWithDriver rentalWithDriver;

    @BeforeEach
    public void init() {
        User user = new User(1, "user1", "password", "Address");
        user.setRole("CUSTOMER");

        Car car = new Car();

        Driver driver = new Driver();
        driver.setDriverId(1);
        driver.setUser(user);
        driver.setDriverAvailability(DriverAvailability.AVAILABLE);
        driver.setLicenseNo("XYZ123");
        driver.setRating(4.5);
        driver.setExperienceYears(5);

        rentalWithDriver = new RentalWithDriver();
        rentalWithDriver.setUser(user);
        rentalWithDriver.setCar(car);
        rentalWithDriver.setDriver(driver);
    }

    @Test
    public void testRentWithDriverSuccess() throws DriverNotAvailable, InvalidIDException, CarNotAvailable {
        when(rentalWithDriverRepository.save(rentalWithDriver)).thenReturn(rentalWithDriver);

        String result = rentalWithDriverService.rentWithDriver(rentalWithDriver, 1, 1, 1);

        assertEquals("Rental was successfully initiated", result);
        verify(rentalWithDriverRepository, times(1)).save(rentalWithDriver);
    }
    
    
}
