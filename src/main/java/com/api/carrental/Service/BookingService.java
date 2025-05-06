package com.api.carrental.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Repository.BookingRepository;
import com.api.carrental.Repository.CarrentalRepository;
import com.api.carrental.Repository.CustomerRepository;
import com.api.carrental.enums.BookingStatus;
import com.api.carrental.model.Booking;
import com.api.carrental.model.Car;
import com.api.carrental.model.Customer;
@Service
public class BookingService {
	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private CarrentalRepository carrentalRepository;

	public List<Booking> getAllBooking() {
        return bookingRepository.findAll();
    }

	public Booking createBooking(Booking booking) {
		// Set initial status
	    booking.setStatus(BookingStatus.PENDING);
	    return bookingRepository.save(booking);
	}


	 public Booking updateStatus(int id, String status) throws InvalidIDException {
	        // Find the booking by ID
	        Optional<Booking> optionalBooking = bookingRepository.findById(id);
	        if (!optionalBooking.isPresent()) {
	            throw new InvalidIDException("Booking ID not found: " + id);
	        }

	        Booking booking = optionalBooking.get();

	        try {
	            // Convert string to enum
	            BookingStatus bookingStatus = BookingStatus.valueOf(status);
	            booking.setStatus(bookingStatus);
	        } catch (IllegalArgumentException e) {
	            throw new IllegalArgumentException("Invalid status value: " + status);
	        }

	        // Save the updated booking
	        return bookingRepository.save(booking);
	    }



	public Booking getBookingById(int id) throws InvalidIDException {
		//tries to find booking in the db by the given id
		Optional<Booking> optional=bookingRepository.findById(id);
		if(optional.isEmpty()) {
			throw new InvalidIDException("Booking not found with id: " + id);
		}
		return optional.get();
	}

	

	

}
