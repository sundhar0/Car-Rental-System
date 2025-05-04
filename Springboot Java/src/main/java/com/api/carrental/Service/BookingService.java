package com.api.carrental.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Repository.BookingRepository;
import com.api.carrental.Repository.CarrentalRepository;
import com.api.carrental.Repository.CustomerRepository;
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
	    int carId = booking.getCar().getId();       // uses carId from Car entity
	    Long customerId = booking.getCustomer().getId(); // uses id from Customer entity
	    
	    /*fetch the car entity from database using carid ensure the car exist if not throws exception*/	    
	    Car car = carrentalRepository.findById(carId)
	        .orElseThrow(() -> new RuntimeException("Car not found"));
	    
	    /*fetch customer entity from db check if customer exist if not throws exception*/

	    Customer customer = customerRepository.findById(customerId)
	        .orElseThrow(() -> new RuntimeException("Customer not found"));
	    
	    //update booking with managed entity
	    booking.setCar(car);
	    booking.setCustomer(customer);

	    return bookingRepository.save(booking);
	}


	public Booking updateBooking(int id, Booking newBookingData) throws InvalidIDException {
	    Optional<Booking> optional = bookingRepository.findById(id);
	    //check if the given id exist
	    if (optional.isEmpty()) {
	        throw new InvalidIDException("Booking not found with id: " + id);
	    }

	    Booking existingBooking = optional.get();

	    // Update relevant fields only
	    existingBooking.setBookingDate(newBookingData.getBookingDate());
	    existingBooking.setReturnDate(newBookingData.getReturnDate());
	    existingBooking.setDriveMode(newBookingData.getDriveMode());
	    
	    return bookingRepository.save(existingBooking);
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
