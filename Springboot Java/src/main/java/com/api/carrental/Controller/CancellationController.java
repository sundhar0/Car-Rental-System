package com.api.carrental.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Service.BookingService;
import com.api.carrental.Service.CancellationService;
import com.api.carrental.enums.BookingStatus;

import com.api.carrental.Service.TestDriveService;

import com.api.carrental.model.Booking;
import com.api.carrental.model.Cancellation;
import com.api.carrental.model.TestDrive;

@RestController
@RequestMapping("/api/cancel")
@CrossOrigin(origins = "http://localhost:5173")
public class CancellationController {
	@Autowired
	private CancellationService cancellationService;
	@Autowired
	private BookingService bookingService;
	@Autowired
	private TestDriveService testDriveService;
	
	// Create a cancellation
	@PostMapping("/add/{bookingId}")
	public Cancellation cancelBooking(@PathVariable int bookingId, @RequestBody Cancellation cancellation) throws InvalidIDException {
	    Booking booking = bookingService.getBookingById(bookingId);

	    //  Set booking status to CANCELLED
	    booking.setStatus(BookingStatus.CANCELLED);  // assuming you have an enum BookingStatus
	    bookingService.createBooking(booking);         // make sure this method updates the booking

	    // Set cancellation details
	    cancellation.setBooking(booking);
	    cancellation.setCancelledDate(LocalDateTime.now());
	    return cancellationService.cancelBooking(cancellation);
	}


    // Get all cancellations
    @GetMapping("/getall")
    public List<Cancellation> getAllCancellations() {
        return cancellationService.getAllCancellations();
    }

    // Get cancellation by booking ID
    @GetMapping("/{bookingId}")
    public Cancellation getByBooking(@PathVariable int bookingId) throws InvalidIDException {
        return cancellationService.getCancellationByBookingId(bookingId);
    }

    //Create cancellation for Test Drive
    @PostMapping("/add/{tdId}")
    public Cancellation cancelTestDrive(@PathVariable int tdId,@RequestBody Cancellation cancellation) throws InvalidIDException {
    	TestDrive testDrive=testDriveService.findById(tdId);
    	cancellation.setTestDrive(testDrive);
    	cancellation.setCancelledDate(LocalDateTime.now());
    	return cancellationService.cancelBooking(cancellation);
    }
    //Get all cancellation by TestDrive Id
    @GetMapping("/{tdId}")
    public Cancellation getByTestDriveId(@PathVariable int tdId) throws InvalidIDException {
    	return cancellationService.getCancellationByTestDriveId(tdId);
    }
	

}
