package com.api.carrental.serviceTest;

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
    public void setUp() {
        user = new User();
        user.setUserId(1);
        user.setUserName("user1");
        user.setPassword("password");
        user.setAddress("Some Address");

        complaint = new Complaint();
        complaint.setComplaintId(1);
        complaint.setIssue("Engine Issue");
        complaint.setStatus("Pending");
    }

    @Test
    public void testAddComplaintSuccess() throws InvalidIDException {
        when(authService.getById(1)).thenReturn(user);
        when(complaintRepository.save(complaint)).thenReturn(complaint);

        Complaint result = complaintService.addComplaint(complaint, 1);
        assertNotNull(result);
        assertEquals("Engine Issue", result.getIssue());
        verify(complaintRepository, times(1)).save(complaint);
    }

    @Test
    public void testAddComplaintInvalidUserId() throws InvalidIDException {
        when(authService.getById(1)).thenReturn(null);
        assertThrows(InvalidIDException.class, () -> complaintService.addComplaint(complaint, 1));
    }

    @Test
    public void testUpdateComplaintSuccess() throws InvalidIDException {
        when(complaintRepository.findById(1)).thenReturn(Optional.of(complaint));

        Complaint updatedComplaint = new Complaint();
        updatedComplaint.setIssue("Window Broken");
        updatedComplaint.setStatus("Resolved");

        when(complaintRepository.save(any(Complaint.class))).thenReturn(updatedComplaint);

        Complaint result = complaintService.updateComplaint(1, updatedComplaint);
        assertEquals("Window Broken", result.getIssue());
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
        assertEquals("Engine Issue", result.getIssue());
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
