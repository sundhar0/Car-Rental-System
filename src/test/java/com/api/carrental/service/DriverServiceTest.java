package com.api.carrental.service;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import java.util.*;

import com.api.carrental.Exception.*;
import com.api.carrental.Repository.DriverRepository;
import com.api.carrental.Service.AuthService;
import com.api.carrental.Service.DriverService;
import com.api.carrental.enums.DriverAvailability;
import com.api.carrental.model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DriverServiceTest {

    @InjectMocks
    private DriverService driverService;

    @Mock
    private DriverRepository driverRepository;

    @Mock
    private AuthService authService;

    private Driver driver;
    private User user;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setUserId(1);
        user.setUserName("testUser");
        user.setPassword("pass");
        user.setRole("DRIVER");

        driver = new Driver();
        driver.setDriverId(1);
        driver.setName("John");
        driver.setLicenseNo("ABC123");
        driver.setExperienceYears(5);
        driver.setRating(4.5);
        driver.setDriverAvailability(DriverAvailability.AVAILABLE);
        driver.setUser(user);
    }

    @Test
    void testAddDriverSuccess() throws Exception {
        when(authService.getById(1)).thenReturn(user);
        when(driverRepository.findByLicenseNo("ABC123")).thenReturn(null);
        when(driverRepository.save(any(Driver.class))).thenReturn(driver);

        Driver savedDriver = driverService.add(driver, 1);

        assertEquals(driver.getLicenseNo(), savedDriver.getLicenseNo());
        verify(driverRepository, times(1)).save(driver);
    }

    @Test
    void testAddDriverWithExistingLicense() throws InvalidIDException {
        when(authService.getById(1)).thenReturn(user);
        when(driverRepository.findByLicenseNo("ABC123")).thenReturn(driver);

        assertThrows(LicenseNoAlreadyAssigned.class, () -> {
            driverService.add(driver, 1);
        });
    }

    @Test
    void testGetDriverByIdSuccess() throws InvalidIDException {
        when(driverRepository.findById(1)).thenReturn(Optional.of(driver));

        Driver result = driverService.getById(1);

        assertEquals("John", result.getName());
    }

    @Test
    void testGetDriverByIdNotFound() {
        when(driverRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(InvalidIDException.class, () -> {
            driverService.getById(1);
        });
    }

    @Test
    void testGetDriverByUsernameSuccess() throws InvalidUserNameException {
        when(driverRepository.findByUserUsername("testUser")).thenReturn(driver);

        Driver result = driverService.getByName("testUser");

        assertEquals("John", result.getName());
    }

    @Test
    void testGetDriverByUsernameNotFound() {
        when(driverRepository.findByUserUsername("unknown")).thenReturn(null);

        assertThrows(InvalidUserNameException.class, () -> {
            driverService.getByName("unknown");
        });
    }

    @Test
    void testGetByAvailableSuccess() throws DriverNotAvailable {
        when(driverRepository.findByDriverAvailability(DriverAvailability.AVAILABLE))
                .thenReturn(Collections.singletonList(driver));

        List<Driver> availableDrivers = driverService.getByAvailable();

        assertFalse(availableDrivers.isEmpty());
    }

    @Test
    void testGetByAvailableNotFound() {
        when(driverRepository.findByDriverAvailability(DriverAvailability.AVAILABLE))
                .thenReturn(null);

        assertThrows(DriverNotAvailable.class, () -> {
            driverService.getByAvailable();
        });
    }

    @Test
    void testUpdateAvailabilitySuccess() throws DriverNotAvailable {
        when(driverRepository.findByDriverId(1)).thenReturn(driver);
        when(driverRepository.save(any())).thenReturn(driver);

        Driver updatedDriver = driverService.updateAvailablility(1, "UNAVAILABLE");

        assertEquals(DriverAvailability.UNAVAILABLE, updatedDriver.getDriverAvailability());
    }

    @Test
    void testUpdateAvailabilityDriverNotFound() {
        when(driverRepository.findByDriverId(2)).thenReturn(null);

        assertThrows(DriverNotAvailable.class, () -> {
            driverService.updateAvailablility(2, "AVAILABLE");
        });
    }

    @Test
    void testDeleteDriverSuccess() throws InvalidIDException {
        when(driverRepository.findById(1)).thenReturn(Optional.of(driver));

        driverService.deleteDriver(1);

        verify(driverRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteDriverNotFound() {
        when(driverRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(InvalidIDException.class, () -> {
            driverService.deleteDriver(99);
        });
    }
}
