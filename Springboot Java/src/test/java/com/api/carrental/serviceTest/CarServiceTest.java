package com.api.carrental.serviceTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Repository.CarApprovalRepository;
import com.api.carrental.Repository.CarRepository;
import com.api.carrental.Service.CarService;
import com.api.carrental.Service.CustomerService;
import com.api.carrental.enums.CarSaleType;
import com.api.carrental.enums.CarStatus;
import com.api.carrental.model.Car;
import com.api.carrental.model.CarApproval;
import com.api.carrental.model.Customer;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
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
    public void testAddCarSetsPendingStatus() {
        car1.setStatus(null);
        when(carRepository.save(car1)).thenReturn(car1);
        Car savedCar = carService.add(car1);
        assertEquals(CarStatus.PENDING, savedCar.getStatus());
        verify(carRepository, times(1)).save(car1);
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
  //testing the approved cars
//    @Test
//    public void testGetAllApprovedCarsForSell() {
//    	//fetching the cars which are approved and stored in arrays
//        List<CarApproval> list = Arrays.asList(approval1, approval2);
//        
//        when(carApprovalRepository.findByApprovedTrue()).thenReturn(list);
//        //fetching the details service methods
//        List<Car> result = carService.getAll();
//        assertEquals(1, result.size());
//        //checking the asserts
//        assertEquals(CarSaleType.SELL, result.get(0).getCarSaleType());
//    }
  //testing the history record
//    @Test
//    public void testGetHistoryValidCustomer() throws InvalidIDException {
//    	//fetching the details by customer id
//        when(customerService.getSingleCustomer(1L)).thenReturn(customer);
//        //if the customer id valid means storing the data
//        when(carRepository.findByCustomerId(1L)).thenReturn(Arrays.asList(car1, car2));
//        List<Car> result = (List<Car>) carService.getHistory(1L);
//        assertEquals(2, result.size());
//        //checking the asserts
//        verify(carRepository, times(1)).findByCustomerId(1L);
//    }
  //testing the invalid customer id
//    @Test
//    public void testGetHistoryInvalidCustomer() throws InvalidIDException {
//        when(customerService.getSingleCustomer(99L)).thenReturn(null);
//        assertThrows(InvalidIDException.class, () -> carService.getHistory(99L));
//    }
//package com.api.carrental.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.when;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.api.carrental.Exception.InvalidIDException;
//import com.api.carrental.Repository.CarApprovalRepository;
//import com.api.carrental.Repository.CarRepository;
//import com.api.carrental.Service.CarService;
//import com.api.carrental.Service.CustomerService;
//import com.api.carrental.enums.CarSaleType;
//import com.api.carrental.enums.CarStatus;
//import com.api.carrental.model.Car;
//import com.api.carrental.model.CarApproval;
//import com.api.carrental.model.Customer;
//
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//public class CarServiceTest {
//
//    @InjectMocks
//    private CarService carService;
//
//    @Mock
//    private CarRepository carRepository;
//
//    @Mock
//    private CarApprovalRepository carApprovalRepository;
//
//    @Mock
//    private CustomerService customerService;
//
//    Car car1, car2;
//    CarApproval approval1, approval2;
//    Customer customer;
//
//    @BeforeEach
//    public void setup() {
//        customer = new Customer(1L, "John Doe", "john@example.com", "1234567890",
//                "123 Street", "DL1234", "idPath", "photoPath");
//
//        car1 = new Car(1, "Model X", "2020", 30000, "Tesla", "Electric", "Automatic",
//                "300 miles", customer, CarStatus.APPROVED, CarSaleType.SELL);
//
//        car2 = new Car(2, "Model Y", "2021", 35000, "Tesla", "Electric", "Automatic",
//                "320 miles", customer, CarStatus.APPROVED, CarSaleType.RENT);
//
//        approval1 = new CarApproval();
//        approval1.setApproved(true);
//        approval1.setCar(car1);
//
//        approval2 = new CarApproval();
//        approval2.setApproved(true);
//        approval2.setCar(car2);
//    }
//
//    //testing car status is assigned to pending or not
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
//  //testing the approved cars
//    @Test
//    public void testGetAllApprovedCarsForSell() {
//    	//fetching the cars which are approved and stored in arrays
//        List<CarApproval> list = Arrays.asList(approval1, approval2);
//        
//        when(carApprovalRepository.findByApprovedTrue()).thenReturn(list);
//        //fetching the details service methods
//        List<Car> result = carService.getAll();
//        assertEquals(1, result.size());
//        //checking the asserts
//        assertEquals(CarSaleType.SELL, result.get(0).getCarSaleType());
//    }
//  //testing the history record
//    @Test
//    public void testGetHistoryValidCustomer() throws InvalidIDException {
//    	//fetching the details by customer id
//        when(customerService.getSingleCustomer(1L)).thenReturn(customer);
//        //if the customer id valid means storing the data
//        when(carRepository.findByCarOwnerId(1L)).thenReturn(Arrays.asList(car1, car2));
//        List<Car> result = (List<Car>) carService.getHistory(1L);
//        assertEquals(2, result.size());
//        //checking the asserts
//        verify(carRepository, times(1)).findByCarOwnerId(1L);
//    }
//  //testing the invalid customer id
//    @Test
//    public void testGetHistoryInvalidCustomer() throws InvalidIDException {
//        when(customerService.getSingleCustomer(99L)).thenReturn(null);
//        assertThrows(InvalidIDException.class, () -> carService.getHistory(99L));
//    }
//}
