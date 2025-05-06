package com.api.carrental.Service;

import java.util.List;
import java.util.Optional;

<<<<<<< HEAD:src/main/java/com/api/carrental/Service/ManagerService.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
=======
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
>>>>>>> 1a6859e55dacab412dbafd30a213695588396b9d:Springboot Java/src/main/java/com/api/carrental/Service/ManagerService.java
import org.springframework.stereotype.Service;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Repository.BookingRepository;
import com.api.carrental.Repository.ManagerRepository;
import com.api.carrental.enums.BookingStatus;
import com.api.carrental.model.Booking;
import com.api.carrental.model.Manager;

@Service
public class ManagerService {
	@Autowired
	private ManagerRepository managerRepository;
	@Autowired
	private BookingRepository bookingRepository;

	Logger logger=LoggerFactory.getLogger("ManagerService");
	//creating manager
	//fetching the details of the manager from API
	//saving the details in manager table
	public Manager addManager(Manager manager) {
		logger.info("Manager Added Successfully");
		return managerRepository.save(manager);
	}
	//getting all the manager
	//use findAll method for fetching all the managers
	//return the values in list
	
	public List<Manager> GetAllManager() {
		List<Manager> list=managerRepository.findAll();
		return list;
	}
	//finding the single manager details
	//fetching the id of the manager
	//returning it into optional value
	public Manager getById(int mId) throws InvalidIDException {
		Optional<Manager> optional=managerRepository.findById(mId);
		if(optional.isEmpty())
			throw new InvalidIDException("Manager Id given is Invalid...");
		return optional.get();
	}
	public ResponseEntity<?> approveOrRejectCancellation(int bookingId, String approvalStatus) throws InvalidIDException {
        // Fetch the booking from the database using the provided bookingId
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new InvalidIDException("Booking not found"));

        if (approvalStatus.equals("approve")) {
            // Update the status to "APPROVED"
            booking.setStatus(BookingStatus.CONFIRMED);
        } else if (approvalStatus.equals("reject")) {
            // Update the status to "REJECTED"
            booking.setStatus(BookingStatus.CANCELLED);
        }

        // Save the updated booking status
        bookingRepository.save(booking);

        // Return a success response
        return ResponseEntity.ok("Booking status updated successfully.");
    }

}
