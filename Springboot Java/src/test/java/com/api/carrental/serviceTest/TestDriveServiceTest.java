package com.api.carrental.serviceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Repository.AuthRepository;
import com.api.carrental.Repository.CarRepository;
import com.api.carrental.Repository.TestDriveRepository;
import com.api.carrental.Service.TestDriveService;
import com.api.carrental.model.Car;
import com.api.carrental.model.TestDrive;
import com.api.carrental.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TestDriveServiceTest {

    @Mock
    private TestDriveRepository testDriveRepository;

    @Mock
    private CarRepository carRepository;

    @Mock
    private AuthRepository userRepository;

    @InjectMocks
    private TestDriveService testDriveService;

    private Car car;
    private User user;
    private TestDrive testDrive;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setUserId(1);
        user.setUserName("johndoe");

        car = new Car();
        car.setId(1);
        car.setModel("Tesla Model 3");

        testDrive = new TestDrive();
        testDrive.setId(1);
        testDrive.setCar(car);
        testDrive.setUser(user);
        testDrive.setBookingDate(LocalDate.of(2025, 5, 5));
        testDrive.setTime("10:00 AM");
        testDrive.setLocation("Main Branch");
    }

    @Test
    public void testAddBooking_Success() throws InvalidIDException {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(carRepository.findById(1)).thenReturn(Optional.of(car));
        when(testDriveRepository.save(any(TestDrive.class))).thenReturn(testDrive);

        TestDrive result = testDriveService.addBooking(1, 1, testDrive);

        assertNotNull(result);
        assertEquals("johndoe", result.getUser().getUsername());
        assertEquals("Tesla Model 3", result.getCar().getModel());
        assertEquals("Main Branch", result.getLocation());
    }

    @Test
    public void testAddBooking_UserNotFound() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());
        when(carRepository.findById(1)).thenReturn(Optional.of(car));

        InvalidIDException exception = assertThrows(InvalidIDException.class, () -> {
            testDriveService.addBooking(1, 1, testDrive);
        });

        assertEquals("User with ID 1 not found", exception.getMessage());
    }

    @Test
    public void testAddBooking_CarNotFound() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(carRepository.findById(1)).thenReturn(Optional.empty());

        InvalidIDException exception = assertThrows(InvalidIDException.class, () -> {
            testDriveService.addBooking(1, 1, testDrive);
        });

        assertEquals("Car with ID 1 not found", exception.getMessage());
    }

    @Test
    public void testGetAllBookings() {
        when(testDriveRepository.findAll()).thenReturn(Arrays.asList(testDrive));
        List<TestDrive> result = testDriveService.getAllBookings();

        assertEquals(1, result.size());
        assertEquals("Tesla Model 3", result.get(0).getCar().getModel());
    }

    @Test
    public void testGetBookingById_Success() throws InvalidIDException {
        when(testDriveRepository.findById(1)).thenReturn(Optional.of(testDrive));
        TestDrive result = testDriveService.getBookingById(1);

        assertEquals(1, result.getId());
        assertEquals("10:00 AM", result.getTime());
    }

    @Test
    public void testGetBookingById_NotFound() {
        when(testDriveRepository.findById(2)).thenReturn(Optional.empty());

        assertThrows(InvalidIDException.class, () -> {
            testDriveService.getBookingById(2);
        });
    }

    @Test
    public void testGetBookingsByCarId_CarNotFound() {
        when(carRepository.existsById(5)).thenReturn(false);

        assertThrows(InvalidIDException.class, () -> {
            testDriveService.getBookingsByCarId(5);
        });
    }

    @Test
    public void testGetBookingsByUserId_UserNotFound() {
        when(userRepository.existsById(10)).thenReturn(false);

        assertThrows(InvalidIDException.class, () -> {
            testDriveService.getBookingsByUserId(10);
        });
    }
}
