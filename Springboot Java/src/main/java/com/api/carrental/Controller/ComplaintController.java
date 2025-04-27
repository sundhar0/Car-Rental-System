package com.api.carrental.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.carrental.Service.ComplaintService;
import com.api.carrental.model.Complaint;
import com.api.carrental.Exception.InvalidIDException;

import java.util.List;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @PostMapping("/{userId}")
    public ResponseEntity<String> addComplaint(@RequestBody Complaint complaint, @PathVariable int userId) {
        try {
            complaintService.addComplaint(complaint, userId);
            return ResponseEntity.ok("Complaint added successfully");
        } catch (InvalidIDException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{complaintId}")
    public ResponseEntity<String> updateComplaint(@PathVariable int complaintId, @RequestBody Complaint updatedComplaint) {
        try {
            complaintService.updateComplaint(complaintId, updatedComplaint);
            return ResponseEntity.ok("Complaint updated successfully");
        } catch (InvalidIDException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{complaintId}")
    public ResponseEntity<String> deleteComplaint(@PathVariable int complaintId) {
        try {
            complaintService.deleteComplaint(complaintId);
            return ResponseEntity.ok("Complaint deleted successfully");
        } catch (InvalidIDException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{complaintId}")
    public ResponseEntity<Complaint> getComplaintById(@PathVariable int complaintId) {
        try {
            Complaint complaint = complaintService.getComplaintById(complaintId);
            return ResponseEntity.ok(complaint);
        } catch (InvalidIDException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Complaint>> getComplaintsByUserId(@PathVariable int userId) {
        List<Complaint> complaints = complaintService.getComplaintsByUserId(userId);
        return ResponseEntity.ok(complaints);
    }

    @GetMapping
    public ResponseEntity<List<Complaint>> getAllComplaints() {
        List<Complaint> complaints = complaintService.getAllComplaints();
        return ResponseEntity.ok(complaints);
    }
}
