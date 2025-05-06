package com.api.carrental.serviceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.api.carrental.Exception.CarNotAvailable;
import com.api.carrental.Exception.DriverNotAvailable;
import com.api.carrental.Exception.InvalidDateException;
import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Repository.RentalWithDriverRepository;
import com.api.carrental.Service.*;
import com.api.carrental.enums.*;
import com.api.carrental.model.*;

@ExtendWith(MockitoExtension.class)
public class RentalWithDriverServiceTest {

    @Mock private CarService carService;
    @Mock private AuthService authService;
    @Mock private DriverService driverService;
    @Mock private DriverScheduleService driverScheduleService;
    @Mock private RentalWithDriverRepository rentalWithDriverRepository;

    @InjectMocks private RentalWithDriverService rentalWithDriverService;

    private RentalWithDriver rentalWithDriver;
    private User user;
    private Car car;
    private Driver driver;

    @BeforeEach
    public void setUp() {
        user = new User(1, "user1", "password", "Address");
        user.setRole("CUSTOMER");

        car = new Car(1, "Model S", "2022", 75000.0, "Tesla", "Electric", "Automatic", "300mi", user, CarStatus.AVAILABLE, CarSaleType.RENT);

        driver = new Driver();
        driver.setDriverId(1);
        driver.setUser(user);
        driver.setDriverAvailability(DriverAvailability.AVAILABLE);

        rentalWithDriver = new RentalWithDriver();
        rentalWithDriver.setRentalStart(LocalDate.now());
        rentalWithDriver.setRentalEnd(LocalDate.now().plusDays(2));
    }

    @Test
    public void testRentWithDriverSuccess() throws Exception {
        when(authService.getById(1)).thenReturn(user);
        when(carService.getById(1)).thenReturn(car);
        when(driverService.getById(1)).thenReturn(driver);

        doNothing().when(driverScheduleService).getAvailableDriversOn(any(), any());
        when(rentalWithDriverRepository.save(any())).thenReturn(rentalWithDriver);

        String result = rentalWithDriverService.rentWithDriver(rentalWithDriver, 1, 1, 1);
        assertEquals("Rental was successfully initiated", result);
        verify(rentalWithDriverRepository, times(1)).save(rentalWithDriver);
    }

    @Test
    public void testRentWithDriverThrowsDriverNotAvailable() throws InvalidIDException {
        driver.setDriverAvailability(DriverAvailability.UNAVAILABLE);
        when(driverService.getById(1)).thenReturn(driver);
        assertThrows(DriverNotAvailable.class,
            () -> rentalWithDriverService.rentWithDriver(rentalWithDriver, 1, 1, 1));
    }

    @Test
    public void testRentWithDriverThrowsCarNotAvailable() throws InvalidIDException {
        car.setStatus(CarStatus.PENDING);
        when(driverService.getById(1)).thenReturn(driver);
        when(carService.getById(1)).thenReturn(car);
        when(authService.getById(1)).thenReturn(user);

        assertThrows(CarNotAvailable.class,
            () -> rentalWithDriverService.rentWithDriver(rentalWithDriver, 1, 1, 1));
    }

    @Test
    public void testGetAll() {
        when(rentalWithDriverRepository.findAll()).thenReturn(Arrays.asList(rentalWithDriver));
        List<RentalWithDriver> result = rentalWithDriverService.getAll();
        assertEquals(1, result.size());
    }

    @Test
    public void testGetByIdSuccess() throws InvalidIDException {
        when(rentalWithDriverRepository.findById(1)).thenReturn(Optional.of(rentalWithDriver));
        RentalWithDriver result = rentalWithDriverService.getById(1);
        assertEquals(rentalWithDriver, result);
    }

    @Test
    public void testGetByIdThrowsInvalidIDException() {
        when(rentalWithDriverRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(InvalidIDException.class, () -> rentalWithDriverService.getById(1));
    }

    @Test
    public void testGetByDateSuccess() throws InvalidDateException {
        List<RentalWithDriver> list = List.of(rentalWithDriver);
        when(rentalWithDriverRepository.findByRentalStartLessThanEqualAndRentalEndGreaterThanEqual(any(), any()))
            .thenReturn(list);
        List<RentalWithDriver> result = rentalWithDriverService.getByDate(LocalDate.now(), LocalDate.now().plusDays(2));
        assertEquals(1, result.size());
    }

    @Test
    public void testGetByDateThrowsException() {
        when(rentalWithDriverRepository.findByRentalStartLessThanEqualAndRentalEndGreaterThanEqual(any(), any()))
            .thenReturn(null);
        assertThrows(InvalidDateException.class, () ->
            rentalWithDriverService.getByDate(LocalDate.now(), LocalDate.now().plusDays(1)));
    }

    @Test
    public void testDeleteRentalWithDriverSuccess() throws InvalidIDException {
        when(rentalWithDriverRepository.findById(1)).thenReturn(Optional.of(rentalWithDriver));
        doNothing().when(rentalWithDriverRepository).deleteById(1);
        rentalWithDriverService.deleteRentalWithDriver(1);
        verify(rentalWithDriverRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteRentalWithDriverThrowsInvalidIDException() {
        when(rentalWithDriverRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(InvalidIDException.class, () -> rentalWithDriverService.deleteRentalWithDriver(1));
    }

    @Test
    public void testUpdateRideStatus() throws InvalidIDException {
        when(rentalWithDriverRepository.findById(1)).thenReturn(Optional.of(rentalWithDriver));
        rentalWithDriverService.updateRideStatus(1, RideStatus.RIDE_COMPLETED);
        assertEquals(RideStatus.RIDE_COMPLETED, rentalWithDriver.getRideStatus());
        verify(rentalWithDriverRepository).save(rentalWithDriver);
    }
}
