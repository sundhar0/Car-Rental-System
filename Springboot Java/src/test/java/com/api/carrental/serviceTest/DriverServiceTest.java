package com.api.carrental.serviceTest;

import com.api.carrental.Exception.*;
import com.api.carrental.Repository.DriverRepository;
import com.api.carrental.Service.AuthService;
import com.api.carrental.Service.DriverService;
import com.api.carrental.enums.DriverAvailability;
import com.api.carrental.model.Driver;
import com.api.carrental.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setUserId(1);
        user.setUserName("testUser");
        user.setPassword("pass");

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
        when(authService.getByUsername("testUser")).thenReturn(null);
        when(authService.add(any(User.class))).thenReturn(user);
        when(driverRepository.findByLicenseNo("ABC123")).thenReturn(null);
        when(driverRepository.save(any(Driver.class))).thenReturn(driver);

        Driver savedDriver = driverService.add(driver);

        assertEquals(driver.getLicenseNo(), savedDriver.getLicenseNo());
        verify(driverRepository).save(driver);
    }

    @Test
    void testAddDriverWithExistingUsername() {
        when(authService.getByUsername("testUser")).thenReturn(user);

        assertThrows(InvalidUserNameException.class, () -> driverService.add(driver));
    }

    @Test
    void testAddDriverWithExistingLicense() throws InvalidUserNameException {
        when(authService.getByUsername("testUser")).thenReturn(null);
        when(authService.add(any(User.class))).thenReturn(user);
        when(driverRepository.findByLicenseNo("ABC123")).thenReturn(driver);

        assertThrows(LicenseNoAlreadyAssigned.class, () -> driverService.add(driver));
    }

    @Test
    void testGetByNameSuccess() throws InvalidUserNameException {
        when(driverRepository.findByUserUsername("testUser")).thenReturn(driver);

        Driver result = driverService.getByName("testUser");

        assertEquals("John", result.getName());
    }

    @Test
    void testGetByNameNotFound() {
        when(driverRepository.findByUserUsername("unknown")).thenReturn(null);

        assertThrows(InvalidUserNameException.class, () -> driverService.getByName("unknown"));
    }

    @Test
    void testGetByIdSuccess() throws InvalidIDException {
        when(driverRepository.findById(1)).thenReturn(Optional.of(driver));

        Driver result = driverService.getById(1);

        assertEquals("John", result.getName());
    }

    @Test
    void testGetByIdNotFound() {
        when(driverRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(InvalidIDException.class, () -> driverService.getById(99));
    }

    @Test
    void testGetAllSuccess() throws DriverNotAvailable {
        Page<Driver> page = new PageImpl<>(List.of(driver));
        when(driverRepository.findAll(any(Pageable.class))).thenReturn(page);

        Page<Driver> result = driverService.getAll(Pageable.unpaged());

        assertEquals(1, result.getTotalElements());
    }

    @Test
    void testGetAllThrowsIfNull() {
        when(driverRepository.findAll(any(Pageable.class))).thenReturn(null);

        assertThrows(DriverNotAvailable.class, () -> driverService.getAll(Pageable.unpaged()));
    }

    @Test
    void testUpdateAvailabilitySuccess() throws DriverNotAvailable {
        when(driverRepository.findByDriverId(1)).thenReturn(driver);
        when(driverRepository.save(any())).thenReturn(driver);

        Driver updated = driverService.updateAvailablility(1, "UNAVAILABLE");

        assertEquals(DriverAvailability.UNAVAILABLE, updated.getDriverAvailability());
    }

    @Test
    void testUpdateAvailabilityNotFound() {
        when(driverRepository.findByDriverId(2)).thenReturn(null);

        assertThrows(DriverNotAvailable.class, () -> driverService.updateAvailablility(2, "AVAILABLE"));
    }

    @Test
    void testDeleteDriverSuccess() throws InvalidIDException {
        when(driverRepository.findById(1)).thenReturn(Optional.of(driver));

        driverService.deleteDriver(1);

        verify(driverRepository).deleteById(1);
    }

    @Test
    void testDeleteDriverNotFound() {
        when(driverRepository.findById(2)).thenReturn(Optional.empty());

        assertThrows(InvalidIDException.class, () -> driverService.deleteDriver(2));
    }

    @Test
    void testUpdateDriverExperienceAndChargeSuccess() throws InvalidIDException {
        when(driverRepository.findById(1)).thenReturn(Optional.of(driver));

        driverService.updateDriverExperienceAndCharge(1, 10, 2000.0);

        assertEquals(10, driver.getExperienceYears());
        assertEquals(2000.0, driver.getPerDayCharge());
        verify(driverRepository).save(driver);
    }

    @Test
    void testUpdateDriverExperienceAndChargeNotFound() {
        when(driverRepository.findById(2)).thenReturn(Optional.empty());

        assertThrows(InvalidIDException.class, () -> driverService.updateDriverExperienceAndCharge(2, 10, 2000.0));
    }

    @Test
    void testUploadImageInvalidExtension() {
        MultipartFile file = mock(MultipartFile.class);
        when(file.getOriginalFilename()).thenReturn("file.txt");

        when(driverRepository.findById(1)).thenReturn(Optional.of(driver));

        assertThrows(RuntimeException.class, () -> driverService.uploadImage(file, 1));
    }

    // Optional: Test successful image upload with mock InputStream and valid extension
    // This test would require more setup (mocking file system behavior) and is usually tested via integration tests.
}
