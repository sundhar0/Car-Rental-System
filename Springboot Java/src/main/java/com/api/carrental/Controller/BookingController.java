package com.api.carrental.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Service.BookingService;
import com.api.carrental.dto.MessageResponseDto;
import com.api.carrental.model.Booking;

//@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/book/")
public class BookingController {
	@Autowired
	private BookingService bookingService;
	@Autowired MessageResponseDto messageDto;
	
	@GetMapping("/getAll")
	public List<Booking> getAllBooking(){
		return bookingService.getAllBooking();
	}
	
	@PostMapping("/create")
	public Booking createBooking(@RequestBody Booking booking) throws InvalidIDException {
		return bookingService.createBooking(booking);
	}
	
	@PutMapping("/updatestatus/{id}/{status}")
	public ResponseEntity<?> updateBookingStatus(@PathVariable int id, @PathVariable String status) {
	    try {
	        Booking updatedBooking = bookingService.updateStatus(id, status.toUpperCase());
	        return ResponseEntity.ok(updatedBooking);
	    } catch (Exception e) {
	        messageDto.setBody(e.getMessage());
	        messageDto.setStatusCode(400);
	        return ResponseEntity.status(400).body(messageDto);
	    }
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<?>getBookingById(@PathVariable int id){
		try {
			Booking booking=bookingService.getBookingById(id);
			return ResponseEntity.ok(booking);
		}catch(InvalidIDException e) {
			messageDto.setBody(e.getMessage());
            messageDto.setStatusCode(400);
            return ResponseEntity.status(400).body(messageDto);
			
		}
	}
	
	
 


}
