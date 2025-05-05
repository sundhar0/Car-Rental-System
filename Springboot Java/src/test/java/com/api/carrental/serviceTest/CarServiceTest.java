package com.api.carrental.serviceTest;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Repository.CarRepository;
import com.api.carrental.Service.CarService;
import com.api.carrental.enums.CarSaleType;
import com.api.carrental.enums.CarStatus;
import com.api.carrental.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    private Car car1;
    private Car car2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        car1 = new Car();
        car1.setId(1);
        car1.setModel("Model X");
        car1.setBrand("Tesla");
        car1.setCarMake("MakeX");
        car1.setCarModel("X");
        car1.setFuelType("Electric");
        car1.setTransmission("Automatic");
        car1.setMileage("10000");
        car1.setCarColor("White");
        car1.setYear("2022");
        car1.setLicensePlateNumber("ABC123");
        car1.setVehicleRegistrationNumber("REG123");
        car1.setStatus(CarStatus.AVAILABLE);
        car1.setCarSaleType(CarSaleType.RENT);
        car1.setPrice(75000.0);

        car2 = new Car();
        car2.setId(2);
        car2.setModel("Model Y");
        car2.setBrand("Tesla");
        car2.setCarMake("MakeY");
        car2.setCarModel("Y");
        car2.setFuelType("Electric");
        car2.setTransmission("Automatic");
        car2.setMileage("5000");
        car2.setCarColor("Black");
        car2.setYear("2023");
        car2.setLicensePlateNumber("XYZ456");
        car2.setVehicleRegistrationNumber("REG456");
        car2.setStatus(CarStatus.APPROVED);
        car2.setCarSaleType(CarSaleType.SELL);
        car2.setPrice(85000.0);
    }

    @Test
    public void testGetAllCars() {
        when(carRepository.findAll()).thenReturn(Arrays.asList(car1, car2));

        List<Car> result = carService.getAll();

        assertEquals(2, result.size());
        verify(carRepository, times(1)).findAll();
    }

    @Test
    public void testGetCarById() throws InvalidIDException {
        when(carRepository.findById(1)).thenReturn(Optional.of(car1));

        Car result = carService.getById(1);

        assertNotNull(result);
        assertEquals("Model X", result.getModel());
        verify(carRepository, times(1)).findById(1);
    }

    @Test
    public void testCreateCar() {
        when(carRepository.save(car1)).thenReturn(car1);

        Car result = carService.add(car1);

        assertNotNull(result);
        assertEquals("Model X", result.getModel());
        verify(carRepository, times(1)).save(car1);
    }

//    @Test
//    public void testUpdateCar_Exists() {
//        when(carRepository.existsById(1)).thenReturn(true);
//        when(carRepository.save(car1)).thenReturn(car1);
//
//        Car result = carService.updateCar(1, car1);
//
//        assertNotNull(result);
//        assertEquals("Model X", result.getModel());
//        verify(carRepository, times(1)).existsById(1);
//        verify(carRepository, times(1)).save(car1);
//    }
//
//    @Test
//    public void testUpdateCar_NotExists() {
//        when(carRepository.existsById(1)).thenReturn(false);
//
//        Car result = carService.updateCar(1, car1);
//
//        assertNull(result);
//        verify(carRepository, times(1)).existsById(1);
//        verify(carRepository, never()).save(any());
//    }

    @Test
    public void testDeleteCar() throws InvalidIDException {
        doNothing().when(carRepository).deleteById(1);

        carService.DeleteCar(1);

        verify(carRepository, times(1)).deleteById(1);
    }
}
