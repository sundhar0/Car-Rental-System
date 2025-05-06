package com.api.carrental.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Service.BookingService;
import com.api.carrental.Service.ManagerService;
import com.api.carrental.dto.MessageResponseDto;
import com.api.carrental.enums.BookingStatus;
import com.api.carrental.model.Booking;
import com.api.carrental.model.Manager;

@RestController
@RequestMapping("/api/Manager")
public class ManagerController {

	@Autowired
	private ManagerService managerService;
	@Autowired
	private BookingService bookingService;
	@Autowired 
	private MessageResponseDto messageDto;
	
	@PostMapping("/add")
	public Manager addManager(@RequestBody Manager manager) {
		return managerService.addManager(manager);
	}
	
	@GetMapping("/getAll")
	public List<Manager> GetAllManager() {
		return managerService.GetAllManager();
	}
	
	@PutMapping("/approvalorreject/{id}")
    public ResponseEntity<?> approveOrRejectCancellation(@PathVariable int id, @RequestBody String approvalStatus) throws InvalidIDException {
        if (approvalStatus.equals("approve") || approvalStatus.equals("reject")) {
            return managerService.approveOrRejectCancellation(id, approvalStatus);
        } else {
            return ResponseEntity.status(400).body("Invalid approval status");
        }
    }
}