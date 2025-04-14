package com.api.carrental.service;

import com.api.carrental.Exception.DriverNotAvailable;
import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Repository.DriverScheduleRepository;
import com.api.carrental.Service.DriverScheduleService;
import com.api.carrental.model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DriverScheduleServiceTest {

    @InjectMocks
    private DriverScheduleService driverScheduleService;

    @Mock
    private DriverScheduleRepository driverScheduleRepository;

    private Driver driver;
    private DriverSchedule schedule;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        driver = new Driver();
        driver.setDriverId(1);
        driver.setName("John Doe");

        schedule = new DriverSchedule();
        schedule.setScheduleId(1);
        schedule.setDriver(driver);
        schedule.setAvailableFrom(LocalDate.of(2025, 4, 10));
        schedule.setAvailableTo(LocalDate.of(2025, 4, 20));
    }

    @Test
    public void testAddOrUpdateByDriverId_NewSchedule() {
        when(driverScheduleRepository.findByDriver_driverId(1)).thenReturn(null);
        when(driverScheduleRepository.save(schedule)).thenReturn(schedule);

        DriverSchedule result = driverScheduleService.addOrUpdateByDriverId(1, schedule);

        assertNotNull(result);
        verify(driverScheduleRepository, times(1)).save(schedule);
    }

    @Test
    public void testAddOrUpdateByDriverId_UpdateExisting() {
        DriverSchedule existing = new DriverSchedule();
        existing.setScheduleId(1);
        existing.setDriver(driver);
        existing.setAvailableFrom(LocalDate.of(2025, 4, 1));
        existing.setAvailableTo(LocalDate.of(2025, 4, 5));

        when(driverScheduleRepository.findByDriver_driverId(1)).thenReturn(existing);
        when(driverScheduleRepository.save(any())).thenReturn(schedule);

        DriverSchedule updated = driverScheduleService.addOrUpdateByDriverId(1, schedule);

        assertEquals(schedule.getAvailableFrom(), updated.getAvailableFrom());
        verify(driverScheduleRepository, times(1)).save(existing);
    }

    @Test
    public void testGetAvailableDriversOn_Success() throws DriverNotAvailable {
        when(driverScheduleRepository.findByAvailableFromLessThanEqualAndAvailableToGreaterThanEqual(
                any(), any())).thenReturn(List.of(schedule));

        List<Driver> drivers = driverScheduleService.getAvailableDriversOn(
                LocalDate.of(2025, 4, 12), LocalDate.of(2025, 4, 12));

        assertFalse(drivers.isEmpty());
        assertEquals(driver.getDriverId(), drivers.get(0).getDriverId());
    }

    @Test
    public void testGetAvailableDriversOn_ThrowsException() {
        when(driverScheduleRepository.findByAvailableFromLessThanEqualAndAvailableToGreaterThanEqual(
                any(), any())).thenReturn(Collections.emptyList());

        assertThrows(DriverNotAvailable.class, () -> {
            driverScheduleService.getAvailableDriversOn(LocalDate.now(), LocalDate.now());
        });
    }

    @Test
    public void testGetAll() {
        when(driverScheduleRepository.findAll()).thenReturn(List.of(schedule));

        List<DriverSchedule> result = driverScheduleService.getAll();

        assertEquals(1, result.size());
    }

    @Test
    public void testGetById_Success() throws InvalidIDException {
        when(driverScheduleRepository.findById(1)).thenReturn(Optional.of(schedule));

        DriverSchedule result = driverScheduleService.getById(1);

        assertNotNull(result);
        assertEquals(1, result.getScheduleId());
    }

    @Test
    public void testGetById_NotFound() {
        when(driverScheduleRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(InvalidIDException.class, () -> {
            driverScheduleService.getById(99);
        });
    }

    @Test
    public void testGetByDriverUsername_Success() {
        when(driverScheduleRepository.findByDriverUserUsername("john")).thenReturn(Optional.of(schedule));

        DriverSchedule result = driverScheduleService.getByDriverUsername("john");

        assertEquals(driver.getDriverId(), result.getDriver().getDriverId());
    }

    @Test
    public void testDeleteDriverSchedule_Success() throws InvalidIDException {
        when(driverScheduleRepository.findById(1)).thenReturn(Optional.of(schedule));

        driverScheduleService.deleteDriverSchedule(1);

        verify(driverScheduleRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteDriverSchedule_NotFound() {
        when(driverScheduleRepository.findById(2)).thenReturn(Optional.empty());

        assertThrows(InvalidIDException.class, () -> {
            driverScheduleService.deleteDriverSchedule(2);
        });
    }
}
