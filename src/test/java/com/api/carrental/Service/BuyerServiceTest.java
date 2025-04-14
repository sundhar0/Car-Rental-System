package com.api.carrental.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
//import com.api.carrental.Repository.CarApprovalRepository;
import com.api.carrental.Repository.CarRepository;
import com.api.carrental.enums.CarSaleType;
import com.api.carrental.enums.CarStatus;
import com.api.carrental.model.Car;
import com.api.carrental.model.CarApproval;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BuyerServiceTest {

	@InjectMocks
	private CarService carService;
	@Mock
	//private CarApprovalRepository carApprovalRepository;
	
	private CarRepository carRepository;
	private Car car1;
    private Car car2;
    private Car car3;
    private CarApproval approval1;
    private CarApproval approval2;
    private CarApproval approval3;
	
	@BeforeEach
    public void setUp() {
        car1 = new Car(1, "Model X", "2020", 1000000, "Tesla", "Electric", "Auto", "5000km", null, CarStatus.AVAILABLE, CarSaleType.SELL);
        car2 = new Car(2, "Civic", "2021", 800000, "Honda", "Petrol", "Manual", "15000km", null, CarStatus.AVAILABLE, CarSaleType.RENT);
        car3 = new Car(3, "City", "2022", 900000, "Honda", "Diesel", "Manual", "10000km", null, CarStatus.AVAILABLE, CarSaleType.SELL);

        approval1 = new CarApproval();
        approval1.setCar(car1);
        approval2 = new CarApproval();
        approval2.setCar(car2);
        approval3 = new CarApproval();
        approval3.setCar(car3);
    }

//    @Test
//    public void testGetAllApprovedSellCars() {
//        List<CarApproval> mockApprovals = Arrays.asList(approval1, approval2, approval3);
//
//        when(carApprovalRepository.findByApprovedTrue()).thenReturn(mockApprovals);
//
//        List<Car> result = carService.getAll();
//
//        assertEquals(2, result.size());
//        assertEquals(CarSaleType.SELL, result.get(0).getCarSaleType());
//        assertEquals(CarSaleType.SELL, result.get(1).getCarSaleType());
//
//        verify(carApprovalRepository, times(1)).findByApprovedTrue();
//    }
    @Test
    public void GetByIdTest() {
    	//Optional<Car> list=Arrays.asList(car1,car2);
    	int cId=1;
    	//when(carRepository.findById(cId)).thenReturn(list);
    }
}

