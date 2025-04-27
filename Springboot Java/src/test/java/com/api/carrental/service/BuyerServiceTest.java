package com.api.carrental.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.carrental.Exception.InvalidFuelException;
import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Exception.InvalidModelException;
import com.api.carrental.Exception.InvalidPriceException;
import com.api.carrental.Exception.InvalidYearException;
import com.api.carrental.Repository.CarApprovalRepository;
//import com.api.carrental.Repository.CarApprovalRepository;
import com.api.carrental.enums.CarSaleType;
import com.api.carrental.model.Car;
import com.api.carrental.model.CarApproval;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BuyerServiceTest {

	@InjectMocks
    private BuyerService buyerService;

    @Mock
    private CarApprovalRepository carApprovalRepository;

    @Mock
    private CarService carService;

    private Car car1, car2;
    private CarApproval approval1, approval2;

    @BeforeEach
    public void setUp() {
        car1 = new Car();
        car1.setCarId(1);
        car1.setModel("Honda");
        car1.setYear("2020");
        car1.setFuelType("Petrol");
        car1.setPrice(500000);
        car1.setCarSaleType(CarSaleType.SELL);

        car2 = new Car();
        car2.setCarId(2);
        car2.setModel("Toyota");
        car2.setYear("2019");
        car2.setFuelType("Diesel");
        car2.setPrice(600000);
        car2.setCarSaleType(CarSaleType.SELL);

        approval1 = new CarApproval();
        approval1.setApproved(true);
        approval1.setCar(car1);

        approval2 = new CarApproval();
        approval2.setApproved(true);
        approval2.setCar(car2);
    }

    //Testing the given model is valid
    @Test
    public void testGetByModel_Valid() throws InvalidModelException {
    	//fetching the carstatus by car approval
    	//its approved means it will store in array
        when(carApprovalRepository.findByApprovedTrue()).thenReturn(Arrays.asList(approval1, approval2));
        //it will store the details details from table
        List<Car> result = (List<Car>) buyerService.getByModel("Honda");
        assertEquals(1, result.size());
        //check the both the asserts are equal or not
        assertEquals("Honda", result.get(0).getModel());
    }

    //testing of gives an invalid model
    @Test
    public void testGetByModel_Invalid() {
    	//storing the details of the cars which are approved
        when(carApprovalRepository.findByApprovedTrue()).thenReturn(Arrays.asList(approval2));
        //if car does not found means it will throw an exception
        assertThrows(InvalidModelException.class, () -> buyerService.getByModel("Honda"));
    }

    //testing the cars fetching by get by year
    @Test
    public void testGetByYear_Valid() throws InvalidYearException {
    	//it will store the values in the arrays after checking the approval
        when(carApprovalRepository.findByApprovedTrue()).thenReturn(Arrays.asList(approval1, approval2));
        //it will store the cars by given year
        List<Car> result = (List<Car>) buyerService.getByYear("2020");
        assertEquals(1, result.size());
        //checking the asserts
        assertEquals("2020", result.get(0).getYear());
    }

    //testing for invalid year
    @Test
    public void testGetByYear_Invalid() {
    	//storing the approved cars
        when(carApprovalRepository.findByApprovedTrue()).thenReturn(Collections.emptyList());
        //if gives invalid year means it will throw a exception
        assertThrows(InvalidYearException.class, () -> buyerService.getByYear("2025"));
    }

    //testing for valid fuel type
    @Test
    public void testGetByFuelType_Valid() throws InvalidFuelException {
    	//storing the approved cars in array
        when(carApprovalRepository.findByApprovedTrue()).thenReturn(Arrays.asList(approval1, approval2));
        //fetching the cars in given fuel type
        List<Car> result = (List<Car>) buyerService.getByFuelType("Petrol");
        assertEquals(1, result.size());
        //checking asserts
        assertEquals("Petrol", result.get(0).getFuelType());
    }

    //testing for invalid fuel type
    @Test
    public void testGetByFuelType_Invalid() {
    	//storing approved cars in array
        when(carApprovalRepository.findByApprovedTrue()).thenReturn(Collections.emptyList());
        //if given fuel type is invalid it will throw the exception
        assertThrows(InvalidFuelException.class, () -> buyerService.getByFuelType("CNG"));
    }

    //testing for valid price
    @Test
    public void testGetByPrice_Valid() throws InvalidPriceException {
    	//storing approved cars in arrays
        when(carApprovalRepository.findByApprovedTrue()).thenReturn(Arrays.asList(approval1));
        //fetching the cars in given price
        List<Car> result = (List<Car>) buyerService.getByPrice(500000);
        assertEquals(1, result.size());
        //checking the asserts
        assertEquals(500000, result.get(0).getPrice());
    }

    //testing for invalid price
    @Test
    public void testGetByPrice_Invalid() {
    	//storing approved cars in arrays
        when(carApprovalRepository.findByApprovedTrue()).thenReturn(Collections.emptyList());
      //if given fuel type is invalid it will throw the exception
        assertThrows(InvalidPriceException.class, () -> buyerService.getByPrice(12345));
    }
  //testing for valid id
    @Test
    public void testGetByCarId_Valid() throws InvalidIDException {
    	//storing approved cars in arrays
        when(carService.getById(1)).thenReturn(car1);
      //fetching the cars in given id
        Car result = (Car) buyerService.getbyCarId(1);
        //checking the asserts 
        assertEquals(1, result.getCarId());
    }

  //testing for invalid id
    @Test
    public void testGetByCarId_Invalid() throws InvalidIDException {
    	//storing approved cars in arrays
        when(carService.getById(100)).thenReturn(null);
      //if given fuel type is invalid it will throw the exception
        assertThrows(InvalidIDException.class, () -> buyerService.getbyCarId(100));
    }
}

