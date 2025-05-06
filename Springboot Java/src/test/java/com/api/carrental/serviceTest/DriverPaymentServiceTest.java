package com.api.carrental.serviceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Exception.InvalidPaymentException;
import com.api.carrental.Repository.DriverPaymentRepository;
import com.api.carrental.Service.DriverPaymentService;
import com.api.carrental.Service.DriverService;
import com.api.carrental.Service.ManagerService;
import com.api.carrental.model.Driver;
import com.api.carrental.model.DriverPayment;
import com.api.carrental.model.Manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DriverPaymentServiceTest {

    @Mock
    private DriverService driverService;

    @Mock
    private ManagerService managerService;

    @Mock
    private DriverPaymentRepository driverPaymentRepository;

    @InjectMocks
    private DriverPaymentService driverPaymentService;

    private DriverPayment driverPayment;
    private Driver driver;
    private Manager manager;

    @BeforeEach
    public void init() {
        driver = new Driver();
        driver.setDriverId(1);
        driver.setName("John");

        manager = new Manager();
        manager.setId(1);
        manager.setName("Manager1");

        driverPayment = new DriverPayment();
        driverPayment.setAmount(1000);
    }

    @Test
    public void testAddDriverPaymentSuccess() throws InvalidIDException {
        when(driverService.getById(1)).thenReturn(driver);
        when(managerService.getById(1)).thenReturn(manager);
        when(driverPaymentRepository.save(any(DriverPayment.class))).thenReturn(driverPayment);

        DriverPayment result = driverPaymentService.add(driverPayment, 1, 1);

        assertEquals(driver, result.getDriver());
        assertEquals(manager, result.getUpdatedBy());
        assertNotNull(result.getPaymentDate());
    }

    @Test
    public void testGetAllPayments() {
        when(driverPaymentRepository.findAll()).thenReturn(Arrays.asList(driverPayment));

        List<DriverPayment> result = driverPaymentService.getAll();

        assertEquals(1, result.size());
    }

    @Test
    public void testGetByIdSuccess() throws InvalidIDException {
        when(driverPaymentRepository.findById(1)).thenReturn(Optional.of(driverPayment));

        DriverPayment result = driverPaymentService.getById(1);

        assertEquals(driverPayment, result);
    }

    @Test
    public void testGetByIdNotFound() {
        when(driverPaymentRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(InvalidIDException.class, () -> driverPaymentService.getById(1));
    }

    @Test
    public void testGetByDriverNameSuccess() throws InvalidPaymentException {
        when(driverPaymentRepository.findByDriverName("John")).thenReturn(Arrays.asList(driverPayment));

        List<DriverPayment> result = driverPaymentService.getByDriverName("John");

        assertEquals(1, result.size());
    }

    @Test
    public void testGetByDriverNameNotFound() {
        when(driverPaymentRepository.findByDriverName("Unknown")).thenReturn(null);

        assertThrows(InvalidPaymentException.class, () -> driverPaymentService.getByDriverName("Unknown"));
    }

    @Test
    public void testGetByDriverIdSuccess() throws InvalidPaymentException {
        when(driverPaymentRepository.findByDriver_DriverId(1)).thenReturn(driverPayment);

        DriverPayment result = driverPaymentService.getByDriverId(1);

        assertEquals(driverPayment, result);
    }

    @Test
    public void testGetByDriverIdNotFound() {
        when(driverPaymentRepository.findByDriver_DriverId(1)).thenReturn(null);

        assertThrows(InvalidPaymentException.class, () -> driverPaymentService.getByDriverId(1));
    }

    @Test
    public void testGetByDateSuccess() throws InvalidPaymentException {
        LocalDate date = LocalDate.now();
        when(driverPaymentRepository.findAllByPaymentDate(date)).thenReturn(Arrays.asList(driverPayment));

        List<DriverPayment> result = driverPaymentService.getByDate(date);

        assertEquals(1, result.size());
    }

    @Test
    public void testGetByDateNotFound() {
        LocalDate date = LocalDate.now();
        when(driverPaymentRepository.findAllByPaymentDate(date)).thenReturn(null);

        assertThrows(InvalidPaymentException.class, () -> driverPaymentService.getByDate(date));
    }

    @Test
    public void testDeleteDriverPaymentSuccess() throws InvalidIDException {
        when(driverPaymentRepository.findById(1)).thenReturn(Optional.of(driverPayment));
        doNothing().when(driverPaymentRepository).deleteById(1);

        driverPaymentService.deleteDriverPayment(1);

        verify(driverPaymentRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteDriverPaymentNotFound() {
        when(driverPaymentRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(InvalidIDException.class, () -> driverPaymentService.deleteDriverPayment(1));
    }
}
