//package com.api.carrental.serviceTest;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.api.carrental.Exception.InvalidIDException;
//import com.api.carrental.Repository.CarRepository;
//import com.api.carrental.Repository.CustomerRepository;
//import com.api.carrental.Repository.TestDriveRepository;
//import com.api.carrental.Service.CarService;
//import com.api.carrental.Service.TestDriveService;
//import com.api.carrental.model.Car;
//import com.api.carrental.model.Customer;
//import com.api.carrental.model.TestDrive;
//
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//public class TestDriveServiceTest {
//
//	@Mock
//    private CustomerService customerService;
//
//    @Mock
//    private CarService carService;
//
//    @Mock
//    private TestDriveRepository testDriveRepository;
//
//    @Mock
//    private CarRepository carRepository;
//
//    @Mock
//    private CustomerRepository customerRepository;
//
//    @InjectMocks
//    private TestDriveService testDriveService;
//
//    private Customer customer;
//    private Car car;
//    private TestDrive testDrive;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//        customer = new Customer();
//        customer.setId(1L);
//        customer.setFullName("John Doe");
//
//        car = new Car();
//        car.setId(1);
//        car.setModel("Honda City");
//
//        testDrive = new TestDrive();
//        testDrive.setId(1);
//        testDrive.setCustomer(user);
//        testDrive.setCar(car);
//        testDrive.setBookingDate("2025-04-14");
//    }
//
//    @Test
//    public void testAddBooking_Success() throws InvalidIDException {
//        when(customerService.getSingleCustomer(1L)).thenReturn(customer);
//        when(carService.getById(1)).thenReturn(car);
//        when(testDriveRepository.save(any(TestDrive.class))).thenReturn(testDrive);
//
//        Object result = testDriveService.addBooking(1, 1L, new TestDrive());
//        assertNotNull(result);
//        assertTrue(result instanceof TestDrive);
//    }
//
//    @Test
//    public void testGetAllBooking_Success() {
//        when(testDriveRepository.findAll()).thenReturn(Arrays.asList(testDrive));
//
//        List<TestDrive> bookings = testDriveService.getAllBooking();
//        assertEquals(1, bookings.size());
//    }
//
//    @Test
//    public void testGetByCarId_Success() throws InvalidIDException {
//        when(carRepository.findById(1)).thenReturn(Optional.of(car));
//
//        Object result = testDriveService.getByCarId(1);
//        assertTrue(result instanceof Car);
//    }
//
//    @Test
//    public void testGetByCustomerId_Success() throws InvalidIDException {
//        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
//
//        Object result = testDriveService.getByCustomerId(1L);
//        assertTrue(result instanceof Customer);
//    }
//
//    @Test
//    public void testFindById_Success() throws InvalidIDException {
//        when(testDriveRepository.findById(1)).thenReturn(Optional.of(testDrive));
//
//        TestDrive result = testDriveService.findById(1);
//        assertEquals(1, result.getId());
//    }
//
//    @Test
//    public void testFindById_InvalidId() {
//        when(testDriveRepository.findById(99)).thenReturn(Optional.empty());
//
//        assertThrows(InvalidIDException.class, () -> testDriveService.findById(99));
//    }
//}
////package com.api.carrental.Service;
////
////import static org.junit.jupiter.api.Assertions.assertEquals;
////import static org.junit.jupiter.api.Assertions.assertNotNull;
////import static org.junit.jupiter.api.Assertions.assertThrows;
////import static org.junit.jupiter.api.Assertions.assertTrue;
////import static org.mockito.ArgumentMatchers.any;
////import static org.mockito.Mockito.when;
////
////import java.util.Arrays;
////import java.util.List;
////import java.util.Optional;
////
////import org.junit.jupiter.api.BeforeEach;
////import org.junit.jupiter.api.Test;
////import org.junit.jupiter.api.extension.ExtendWith;
////import org.mockito.InjectMocks;
////import org.mockito.Mock;
////import org.mockito.MockitoAnnotations;
////import org.mockito.junit.jupiter.MockitoExtension;
////import org.springframework.boot.test.context.SpringBootTest;
////
////import com.api.carrental.Exception.InvalidIDException;
////import com.api.carrental.Repository.CarRepository;
////import com.api.carrental.Repository.CustomerRepository;
////import com.api.carrental.Repository.TestDriveRepository;
////import com.api.carrental.model.Car;
////import com.api.carrental.model.Customer;
////import com.api.carrental.model.TestDrive;
////
////@SpringBootTest
////@ExtendWith(MockitoExtension.class)
////public class TestDriveServiceTest {
////
////	@Mock
////    private CustomerService customerService;
////
////    @Mock
////    private CarService carService;
////
////    @Mock
////    private TestDriveRepository testDriveRepository;
////
////    @Mock
////    private CarRepository carRepository;
////
////    @Mock
////    private CustomerRepository customerRepository;
////
////    @InjectMocks
////    private TestDriveService testDriveService;
////
////    private Customer customer;
////    private Car car;
////    private TestDrive testDrive;
////
////    @BeforeEach
////    public void setUp() {
////        MockitoAnnotations.openMocks(this);
////
////        customer = new Customer();
////        customer.setId(1L);
////        customer.setFullName("John Doe");
////
////        car = new Car();
////        car.setCarId(1);
////        car.setModel("Honda City");
////
////        testDrive = new TestDrive();
////        testDrive.setId(1);
////        testDrive.setCustomer(customer);
////        testDrive.setCar(car);
////        testDrive.setBookingDate("2025-04-14");
////    }
////
////    @Test
////    public void testAddBooking_Success() throws InvalidIDException {
////        when(customerService.getSingleCustomer(1L)).thenReturn(customer);
////        when(carService.getById(1)).thenReturn(car);
////        when(testDriveRepository.save(any(TestDrive.class))).thenReturn(testDrive);
////
////        Object result = testDriveService.addBooking(1, 1L, new TestDrive());
////        assertNotNull(result);
////        assertTrue(result instanceof TestDrive);
////    }
////
////    @Test
////    public void testGetAllBooking_Success() {
////        when(testDriveRepository.findAll()).thenReturn(Arrays.asList(testDrive));
////
////        List<TestDrive> bookings = testDriveService.getAllBooking();
////        assertEquals(1, bookings.size());
////    }
////
////    @Test
////    public void testGetByCarId_Success() throws InvalidIDException {
////        when(carRepository.findById(1)).thenReturn(Optional.of(car));
////
////        Object result = testDriveService.getByCarId(1);
////        assertTrue(result instanceof Car);
////    }
////
////    @Test
////    public void testGetByCustomerId_Success() throws InvalidIDException {
////        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
////
////        Object result = testDriveService.getByCustomerId(1L);
////        assertTrue(result instanceof Customer);
////    }
////
////    @Test
////    public void testFindById_Success() throws InvalidIDException {
////        when(testDriveRepository.findById(1)).thenReturn(Optional.of(testDrive));
////
////        TestDrive result = testDriveService.findById(1);
////        assertEquals(1, result.getId());
////    }
////
////    @Test
////    public void testFindById_InvalidId() {
////        when(testDriveRepository.findById(99)).thenReturn(Optional.empty());
////
////        assertThrows(InvalidIDException.class, () -> testDriveService.findById(99));
////    }
////}
