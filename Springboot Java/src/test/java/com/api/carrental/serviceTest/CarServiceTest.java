package com.api.carrental.serviceTest;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Repository.CarApprovalRepository;
import com.api.carrental.Repository.CarRepository;
import com.api.carrental.Service.AuthService;
import com.api.carrental.Service.CarService;
import com.api.carrental.enums.CarStatus;
import com.api.carrental.model.Car;
import com.api.carrental.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarApprovalRepository carApprovalRepository;

    @Mock
    private AuthService authService;

    @InjectMocks
    private CarService carService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCar_ShouldSetStatusPendingAndSave() {
        Car car = new Car();
        car.setCarModel("Test Model");

        when(carRepository.save(any(Car.class))).thenAnswer(i -> i.getArgument(0));

        Car savedCar = carService.add(car);

        assertEquals(CarStatus.PENDING, savedCar.getStatus());
        verify(carRepository).save(car);
    }

    @Test
    void testGetById_ShouldReturnCar() throws InvalidIDException {
        Car car = new Car();
        car.setId(1);

        when(carRepository.findById(1)).thenReturn(Optional.of(car));

        Car result = carService.getById(1);

        assertEquals(1, result.getId());
        verify(carRepository).findById(1);
    }

    @Test
    void testGetById_InvalidId_ShouldThrowException() {
        when(carRepository.findById(2)).thenReturn(Optional.empty());

        assertThrows(InvalidIDException.class, () -> carService.getById(2));
    }

    @Test
    void testGetHistory_ShouldReturnUserCars() throws InvalidIDException {
        User user = new User();
        user.setUserId(1);

        when(authService.getById(1)).thenReturn(user);
        when(carRepository.findByCarOwnerUserId(1)).thenReturn(java.util.List.of(new Car()));

        Object result = carService.getHistory(1);

        assertNotNull(result);
        verify(authService).getById(1);
        verify(carRepository).findByCarOwnerUserId(1);
    }

    @Test
    void testGetHistory_InvalidUserId_ShouldThrowException() throws InvalidIDException {
        when(authService.getById(2)).thenReturn(null);

        assertThrows(InvalidIDException.class, () -> carService.getHistory(2));
    }

    @Test
    void testDeleteCar_ShouldDeleteCar() throws InvalidIDException {
        Car car = new Car();
        car.setId(1);

        when(carRepository.findById(1)).thenReturn(Optional.of(car));

        carService.DeleteCar(1);

        verify(carRepository).deleteById(1);
    }

    @Test
    void testDeleteCar_InvalidId_ShouldThrowException() {
        when(carRepository.findById(2)).thenReturn(Optional.empty());

        assertThrows(InvalidIDException.class, () -> carService.DeleteCar(2));
    }

    @Test
    void testUpdateCar_ShouldUpdateValues() throws InvalidIDException {
        Car oldCar = new Car();
        oldCar.setId(1);
        oldCar.setCarModel("Old");

        Car newCar = new Car();
        newCar.setCarModel("New");
        newCar.setCarMake("Make");
        newCar.setYear("2022");
        newCar.setLicensePlateNumber("ABC123");
        newCar.setVehicleRegistrationNumber("REG999");
        newCar.setCarColor("Blue");

        when(carRepository.findById(1)).thenReturn(Optional.of(oldCar));
        when(carRepository.save(any(Car.class))).thenAnswer(i -> i.getArgument(0));

        Car updated = (Car) carService.updateCar(1, newCar);

        assertEquals("New", updated.getCarModel());
        assertEquals("Make", updated.getCarMake());
        assertEquals("2022", updated.getYear());
        assertEquals("ABC123", updated.getLicensePlateNumber());
        assertEquals("REG999", updated.getVehicleRegistrationNumber());
        assertEquals("Blue", updated.getCarColor());
    }

    @Test
    void testUpdateCar_InvalidId_ShouldThrowException() {
        when(carRepository.findById(5)).thenReturn(Optional.empty());

        assertThrows(InvalidIDException.class, () -> carService.updateCar(5, new Car()));
    }
}
