package com.api.carrental.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger=LoggerFactory.getLogger("ComplaintService");
    //Adding the complaint by user id
    //fetching the user details and setting in the table
    //add the complaint in the table
    public Complaint addComplaint(Complaint complaint, int userId) throws InvalidIDException {
        User user = authService.getById(userId);
        if(user == null)
        		throw new InvalidIDException("Invalid User ID");
        complaint.setUser(user);
        logger.info("Complaint Added successFully...");
        return complaintRepository.save(complaint);
    }
    //updating the complaint
    //fetch the updated values
    //store it in the table
    public Complaint updateComplaint(int complaintId, Complaint updatedComplaint) throws InvalidIDException {
        Complaint existing = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new InvalidIDException("Complaint not found"));
        existing.setReponse(updatedComplaint.getReponse());
        
        logger.info("Values Updated..");
        return complaintRepository.save(existing);
    }
    
    public Complaint updaterespond(int complaintId, String response, String status) throws InvalidIDException {
        Complaint existing = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new InvalidIDException("Complaint not found"));
        existing.setReponse(response);
        existing.setStatus(status);

        logger.info("Values Updated..");
        return complaintRepository.save(existing);
    }
    //deleting the complaint
    //fetch the complaint by its id
    //delete the id
    public void deleteComplaint(int complaintId) throws InvalidIDException {
        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new InvalidIDException("Complaint not found"));
        logger.info("Complaint Deleted..");
        complaintRepository.delete(complaint);
    }
    //get the complaint by its id
    public Complaint getComplaintById(int complaintId) throws InvalidIDException {
        return complaintRepository.findById(complaintId)
                .orElseThrow(() -> new InvalidIDException("Complaint not found"));
    }
    //get the complaint by it user id
    public List<Complaint> getComplaintsByUserId(int userId) {
        return complaintRepository.findByUserUserId(userId);
    }
    //get all the complaints from the table
    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }
}
