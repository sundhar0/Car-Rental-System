//package com.api.carrental.serviceTest;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
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
//import org.springframework.data.domain.Page;
//import com.api.carrental.Exception.InvalidIDException;
//import com.api.carrental.Repository.CarApprovalRepository;
//import com.api.carrental.Repository.CarRepository;
//import com.api.carrental.Service.AuthService;
//import com.api.carrental.Service.CarService;
//import com.api.carrental.enums.CarStatus;
//import com.api.carrental.model.Car;
//import com.api.carrental.model.User;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class CarServiceTest {
//
//    @Mock
//    private CarRepository carRepository;
//
//    @Mock
//    private CarApprovalRepository carApprovalRepository;
//
//    @Mock
//    private AuthService authService;
//
//    @InjectMocks
//    private CarService carService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    public void setup() {
//        customer = new Customer(1, "John Doe", "john@example.com", "1234567890",
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
//    @Test
//    void testAddCar_ShouldSetStatusPendingAndSave() {
//        Car car = new Car();
//        car.setCarModel("Test Model");
//
//        when(carRepository.save(any(Car.class))).thenAnswer(i -> i.getArgument(0));
//
//        Car savedCar = carService.add(car);
//
//        assertEquals(CarStatus.PENDING, savedCar.getStatus());
//        verify(carRepository).save(car);
//    }
//
//    @Test
//    void testGetById_ShouldReturnCar() throws InvalidIDException {
//        Car car = new Car();
//        car.setId(1);
//
//        when(carRepository.findById(1)).thenReturn(Optional.of(car));
//
//        Car result = carService.getById(1);
//
//        assertEquals(1, result.getId());
//        verify(carRepository).findById(1);
//    }
//
//    @Test
//    void testGetById_InvalidId_ShouldThrowException() {
//        when(carRepository.findById(2)).thenReturn(Optional.empty());
//
//        assertThrows(InvalidIDException.class, () -> carService.getById(2));
//    }
//
//    @Test
//    void testGetHistory_ShouldReturnUserCars() throws InvalidIDException {
//        User user = new User();
//        user.setUserId(1);
//
//        when(authService.getById(1)).thenReturn(user);
//        when(carRepository.findByCarOwnerUserId(1)).thenReturn(java.util.List.of(new Car()));
//
//        Object result = carService.getHistory(1);
//
//        assertNotNull(result);
//        verify(authService).getById(1);
//        verify(carRepository).findByCarOwnerUserId(1);
//    }
//
//    @Test
//    void testGetHistory_InvalidUserId_ShouldThrowException() throws InvalidIDException {
//        when(authService.getById(2)).thenReturn(null);
//
//        assertThrows(InvalidIDException.class, () -> carService.getHistory(2));
//    }
//
//    @Test
//    void testDeleteCar_ShouldDeleteCar() throws InvalidIDException {
//        Car car = new Car();
//        car.setId(1);
//
//        when(carRepository.findById(1)).thenReturn(Optional.of(car));
//
//        carService.DeleteCar(1);
//
//        verify(carRepository).deleteById(1);
//    }
//
//    @Test
//    void testDeleteCar_InvalidId_ShouldThrowException() {
//        when(carRepository.findById(2)).thenReturn(Optional.empty());
//
//        assertThrows(InvalidIDException.class, () -> carService.DeleteCar(2));
//    }
//
//    @Test
//    void testUpdateCar_ShouldUpdateValues() throws InvalidIDException {
//        Car oldCar = new Car();
//        oldCar.setId(1);
//        oldCar.setCarModel("Old");
//
//        Car newCar = new Car();
//        newCar.setCarModel("New");
//        newCar.setCarMake("Make");
//        newCar.setYear("2022");
//        newCar.setLicensePlateNumber("ABC123");
//        newCar.setVehicleRegistrationNumber("REG999");
//        newCar.setCarColor("Blue");
//
//        when(carRepository.findById(1)).thenReturn(Optional.of(oldCar));
//        when(carRepository.save(any(Car.class))).thenAnswer(i -> i.getArgument(0));
//
//        Car updated = (Car) carService.updateCar(1, newCar);
//
//        assertEquals("New", updated.getCarModel());
//        assertEquals("Make", updated.getCarMake());
//        assertEquals("2022", updated.getYear());
//        assertEquals("ABC123", updated.getLicensePlateNumber());
//        assertEquals("REG999", updated.getVehicleRegistrationNumber());
//        assertEquals("Blue", updated.getCarColor());
//    }
//
//    @Test
//    void testUpdateCar_InvalidId_ShouldThrowException() {
//        when(carRepository.findById(5)).thenReturn(Optional.empty());
//
//        assertThrows(InvalidIDException.class, () -> carService.updateCar(5, new Car()));
//    }
//  //testing the approved cars
//    @Test
//    public void testGetAllApprovedCarsForSell() {
//    	//fetching the cars which are approved and stored in arrays
//        List<CarApproval> list = Arrays.asList(approval1, approval2);
//        
//        when(carApprovalRepository.findByApprovedTrue()).thenReturn(list);
//        //fetching the details service methods
//        Page<Car> result = carService.getAll(null);
//        assertEquals(1, result.getSize());
//        //checking the asserts
////        assertEquals(CarSaleType.SELL, result.get(0).getCarSaleType());
//    }
//  //testing the history record
//    @Test
//    public void testGetHistoryValidCustomer() throws InvalidIDException {
//    	//fetching the details by customer id
//        when(customerService.getSingleCustomer(1)).thenReturn(customer);
//        //if the customer id valid means storing the data
//        when(carRepository.findByCarOwnerUserId(1)).thenReturn(Arrays.asList(car1, car2));
//        List<Car> result = carService.getHistory(1);
//        assertEquals(2, result.size());
//        //checking the asserts
//        verify(carRepository, times(1)).findByCarOwnerUserId(1);
//    }
// // testing the invalid customer id
//    @Test
//    public void testGetHistoryInvalidCustomer() throws InvalidIDException {
//        when(customerService.getSingleCustomer(99)).thenReturn(null);
//        assertThrows(InvalidIDException.class, () -> carService.getHistory(99));
//    }
//}
