package com.api.carrental.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.List;
import java.util.Arrays;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Repository.ComplaintRepository;
import com.api.carrental.Service.ComplaintService;
import com.api.carrental.Service.AuthService;
import com.api.carrental.model.Complaint;
import com.api.carrental.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ComplaintServiceTest {

    @Mock
    private ComplaintRepository complaintRepository;

    @Mock
    private AuthService authService;

    @InjectMocks
    private ComplaintService complaintService;

    private Complaint complaint;
    private User user;

    @BeforeEach
    public void init() {
        user = new User(1, "user1", "password", "Address");
        complaint = new Complaint();
        complaint.setComplaintId(1);
        complaint.setIssue("Damaged Car");
        complaint.setStatus("Pending");
    }

    @Test
    public void testAddComplaintSuccess() throws InvalidIDException {
        when(authService.getById(1)).thenReturn(user);
        when(complaintRepository.save(complaint)).thenReturn(complaint);

        Complaint result = complaintService.addComplaint(complaint, 1);
        assertNotNull(result);
        assertEquals("Damaged Car", result.getIssue());
        verify(complaintRepository, times(1)).save(complaint);
    }

    @Test
    public void testAddComplaintInvalidUser() throws InvalidIDException {
        when(authService.getById(1)).thenReturn(null);

        assertThrows(InvalidIDException.class, () -> complaintService.addComplaint(complaint, 1));
    }

    @Test
    public void testUpdateComplaintSuccess() throws InvalidIDException {
        when(complaintRepository.findById(1)).thenReturn(Optional.of(complaint));
        complaint.setIssue("Car was dirty");
        complaint.setStatus("Resolved");
        when(complaintRepository.save(complaint)).thenReturn(complaint);

        Complaint updatedComplaint = new Complaint();
        updatedComplaint.setIssue("Car was dirty");
        updatedComplaint.setStatus("Resolved");

        Complaint result = complaintService.updateComplaint(1, updatedComplaint);
        assertEquals("Car was dirty", result.getIssue());
        assertEquals("Resolved", result.getStatus());
    }

    @Test
    public void testUpdateComplaintNotFound() {
        when(complaintRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(InvalidIDException.class, () -> complaintService.updateComplaint(1, complaint));
    }

    @Test
    public void testDeleteComplaintSuccess() throws InvalidIDException {
        when(complaintRepository.findById(1)).thenReturn(Optional.of(complaint));
        complaintService.deleteComplaint(1);
        verify(complaintRepository, times(1)).delete(complaint);
    }

    @Test
    public void testDeleteComplaintNotFound() {
        when(complaintRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(InvalidIDException.class, () -> complaintService.deleteComplaint(1));
    }

    @Test
    public void testGetComplaintByIdSuccess() throws InvalidIDException {
        when(complaintRepository.findById(1)).thenReturn(Optional.of(complaint));
        Complaint result = complaintService.getComplaintById(1);
        assertEquals("Damaged Car", result.getIssue());
    }

    @Test
    public void testGetComplaintByIdNotFound() {
        when(complaintRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(InvalidIDException.class, () -> complaintService.getComplaintById(1));
    }

    @Test
    public void testGetComplaintsByUserId() {
        when(complaintRepository.findByUserUserId(1)).thenReturn(Arrays.asList(complaint));
        List<Complaint> result = complaintService.getComplaintsByUserId(1);
        assertEquals(1, result.size());
    }

    @Test
    public void testGetAllComplaints() {
        when(complaintRepository.findAll()).thenReturn(Arrays.asList(complaint));
        List<Complaint> result = complaintService.getAllComplaints();
        assertEquals(1, result.size());
    }
}
