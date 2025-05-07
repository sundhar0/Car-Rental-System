package com.api.carrental.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

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

    @InjectMocks
    private CarService carService;

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarApprovalRepository carApprovalRepository;

    @Mock
    private CustomerService customerService;

    Car car1, car2;
    CarApproval approval1, approval2;
    Customer customer;

    @BeforeEach
    public void setup() {
        customer = new Customer(1, "John Doe", "john@example.com", "1234567890",
                "123 Street", "DL1234", "idPath", "photoPath");

        car1 = new Car(1, "Model X", "2020", 30000, "Tesla", "Electric", "Automatic",
                "300 miles", customer, CarStatus.APPROVED, CarSaleType.SELL);

        car2 = new Car(2, "Model Y", "2021", 35000, "Tesla", "Electric", "Automatic",
                "320 miles", customer, CarStatus.APPROVED, CarSaleType.RENT);

        approval1 = new CarApproval();
        approval1.setApproved(true);
        approval1.setCar(car1);

        approval2 = new CarApproval();
        approval2.setApproved(true);
        approval2.setCar(car2);
    }

    //testing car status is assigned to pending or not
    @Test
    public void testAddCarSetsPendingStatus() {
        car1.setStatus(null);
        when(carRepository.save(car1)).thenReturn(car1);
        Car savedCar = carService.add(car1);
        assertEquals(CarStatus.PENDING, savedCar.getStatus());
        verify(carRepository, times(1)).save(car1);
    }

    //testing the valid car id
    @Test
    public void testGetCarByIdValid() throws InvalidIDException {
    	//fetching the car details
        when(carRepository.findById(1)).thenReturn(Optional.of(car1));
        Car result = carService.getById(1);
        //checking the asserts equals or not
        assertEquals("Model X", result.getModel());
        //verifying the car id
        verify(carRepository, times(1)).findById(1);
    }

  //testing the valid car id
    @Test
    public void testGetCarByIdInvalid() {
    	//fetching the car details
        when(carRepository.findById(1)).thenReturn(Optional.empty());
        //if not valid throw the exception
        assertThrows(InvalidIDException.class, () -> carService.getById(1));
    }
  //testing the approved cars
    @Test
    public void testGetAllApprovedCarsForSell() {
    	//fetching the cars which are approved and stored in arrays
        List<CarApproval> list = Arrays.asList(approval1, approval2);
        
        when(carApprovalRepository.findByApprovedTrue()).thenReturn(list);
        //fetching the details service methods
        Page<Car> result = carService.getAll(null);
        assertEquals(1, result.getSize());
        //checking the asserts
//        assertEquals(CarSaleType.SELL, result.get(0).getCarSaleType());
    }
  //testing the history record
    @Test
    public void testGetHistoryValidCustomer() throws InvalidIDException {
    	//fetching the details by customer id
        when(customerService.getSingleCustomer(1)).thenReturn(customer);
        //if the customer id valid means storing the data
        when(carRepository.findByCustomerId(1)).thenReturn(Arrays.asList(car1, car2));
        List<Car> result = carService.getHistory(1);
        assertEquals(2, result.size());
        //checking the asserts
        verify(carRepository, times(1)).findByCustomerId(1);
    }
 // testing the invalid customer id
    @Test
    public void testGetHistoryInvalidCustomer() throws InvalidIDException {
        when(customerService.getSingleCustomer(99)).thenReturn(null);
        assertThrows(InvalidIDException.class, () -> carService.getHistory(99));
    }
}
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
//    public void testAddCarSetsPendingStatus() {
//        car1.setStatus(null);
//        when(carRepository.save(car1)).thenReturn(car1);
//        Car savedCar = carService.add(car1);
//        assertEquals(CarStatus.PENDING, savedCar.getStatus());
//        verify(carRepository, times(1)).save(car1);
//    }
//
//    //testing the valid car id
//    @Test
//    public void testGetCarByIdValid() throws InvalidIDException {
//    	//fetching the car details
//        when(carRepository.findById(1)).thenReturn(Optional.of(car1));
//        Car result = carService.getById(1);
//        //checking the asserts equals or not
//        assertEquals("Model X", result.getModel());
//        //verifying the car id
//        verify(carRepository, times(1)).findById(1);
//    }
//
//  //testing the valid car id
//    @Test
//    public void testGetCarByIdInvalid() {
//    	//fetching the car details
//        when(carRepository.findById(1)).thenReturn(Optional.empty());
//        //if not valid throw the exception
//        assertThrows(InvalidIDException.class, () -> carService.getById(1));
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
