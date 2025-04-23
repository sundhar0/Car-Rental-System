package com.api.carrental.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Service.BookingService;
import com.api.carrental.Service.CancellationService;
import com.api.carrental.model.Booking;
import com.api.carrental.model.Cancellation;

@RestController
@RequestMapping("/api/cancel")
public class CancellationController {
	@Autowired
	private CancellationService cancellationService;
	@Autowired
	private BookingService bookingService;
	
	// Create a cancellation
    @PostMapping("/add/{bookingId}")
    public Cancellation cancelBooking(@PathVariable int bookingId, @RequestBody Cancellation cancellation) throws InvalidIDException {
        Booking booking = bookingService.getBookingById(bookingId);
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

	

}
