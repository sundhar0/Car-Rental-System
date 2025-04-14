package com.api.carrental.Service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Repository.ComplaintRepository;
import com.api.carrental.model.Complaint;
import com.api.carrental.model.User;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private AuthService authService;

    public Complaint addComplaint(Complaint complaint, int userId) throws InvalidIDException {
        User user = authService.getById(userId);
        if(user == null)
        		throw new InvalidIDException("Invalid User ID");
        complaint.setUser(user);
        return complaintRepository.save(complaint);
    }

    public Complaint updateComplaint(int complaintId, Complaint updatedComplaint) throws InvalidIDException {
        Complaint existing = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new InvalidIDException("Complaint not found"));
        existing.setIssue(updatedComplaint.getIssue());
        existing.setStatus(updatedComplaint.getStatus());
        return complaintRepository.save(existing);
    }

    public void deleteComplaint(int complaintId) throws InvalidIDException {
        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new InvalidIDException("Complaint not found"));
        complaintRepository.delete(complaint);
    }

    public Complaint getComplaintById(int complaintId) throws InvalidIDException {
        return complaintRepository.findById(complaintId)
                .orElseThrow(() -> new InvalidIDException("Complaint not found"));
    }

    public List<Complaint> getComplaintsByUserId(int userId) {
        return complaintRepository.findByUserUserId(userId);
    }

    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }
}
