package com.api.carrental.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.List;
import java.util.Arrays;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Repository.CustomerCareRepository;
import com.api.carrental.Service.CustomerCareService;
import com.api.carrental.model.CustomerCare;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CustomerCareServiceTest {

    @Mock
    private CustomerCareRepository customerCareRepository;

    @InjectMocks
    private CustomerCareService customerCareService;

    private CustomerCare customerCare;

    @BeforeEach
    public void init() {
        customerCare = new CustomerCare();
        customerCare.setSupportId(1);
        customerCare.setIssueType("Technical");
        customerCare.setMessage("System not working");
    }

    @Test
    public void testAddCustomerCare() {
        customerCareService.addCustomerCare(customerCare);
        verify(customerCareRepository, times(1)).save(customerCare);
    }

    @Test
    public void testUpdateCustomerCareSuccess() throws InvalidIDException {
        when(customerCareRepository.findById(1)).thenReturn(Optional.of(customerCare));
        customerCareService.updateCustomerCare(1, customerCare);
        verify(customerCareRepository, times(1)).save(customerCare);
    }

    @Test
    public void testUpdateCustomerCareInvalidId() {
        when(customerCareRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(InvalidIDException.class, () -> customerCareService.updateCustomerCare(1, customerCare));
    }

    @Test
    public void testDeleteCustomerCareSuccess() throws InvalidIDException {
        when(customerCareRepository.findById(1)).thenReturn(Optional.of(customerCare));
        customerCareService.deleteCustomerCare(1);
        verify(customerCareRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteCustomerCareInvalidId() {
        when(customerCareRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(InvalidIDException.class, () -> customerCareService.deleteCustomerCare(1));
    }

    @Test
    public void testGetCustomerCareByIdSuccess() throws InvalidIDException {
        when(customerCareRepository.findById(1)).thenReturn(Optional.of(customerCare));
        CustomerCare result = customerCareService.getCustomerCareById(1);
        assertEquals("Technical", result.getIssueType());
    }

    @Test
    public void testGetCustomerCareByIdInvalid() {
        when(customerCareRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(InvalidIDException.class, () -> customerCareService.getCustomerCareById(1));
    }

    @Test
    public void testGetAllCustomerCare() {
        when(customerCareRepository.findAll()).thenReturn(Arrays.asList(customerCare));
        List<CustomerCare> list = customerCareService.getAllCustomerCare();
        assertEquals(1, list.size());
    }
}
