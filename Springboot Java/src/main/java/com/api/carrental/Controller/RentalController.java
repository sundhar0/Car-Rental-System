package com.api.carrental.Controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Service.BookingService;
import com.api.carrental.Service.CarService;
import com.api.carrental.Service.CustomerService;
import com.api.carrental.Service.RentalService;
import com.api.carrental.enums.RentalStatus;
import com.api.carrental.model.Booking;
import com.api.carrental.model.Car;
import com.api.carrental.model.Customer;
import com.api.carrental.model.Rental;

@RestController
@RequestMapping("/api/rental-history")
public class RentalController {
	@Autowired
    private RentalService rentalService;

    @Autowired
    private CarService carService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BookingService bookingService;
    
 // Create Rental
    @PostMapping("/add")
    public Rental createRental(@RequestBody Rental rental) throws InvalidIDException {
        Car car = carService.getById(rental.getCar().getId());
        Customer customer = customerService.getSingleCustomer(rental.getCustomer().getId());
        Booking booking = bookingService.getBookingById(rental.getBooking().getId());

        rental.setCar(car);
        rental.setCustomer(customer);
        rental.setBooking(booking);
        rental.setStatus(RentalStatus.RUNNING);
        return rentalService.saveRental(rental);
    }
 // Update Rental Status
    @PutMapping("/{rentalId}/status")
    public ResponseEntity<Rental> updateRentalStatus(@PathVariable int rentalId,
                                                     @RequestBody Map<String, String> request) throws InvalidIDException {
        RentalStatus status = RentalStatus.valueOf(request.get("status").toUpperCase());
        return ResponseEntity.ok(rentalService.updateRentalStatus(rentalId, status));
    }

    
 // Return Rental
    @PutMapping("/{rentalId}/return")
    public Rental returnRental(@PathVariable int rentalId, @RequestParam String returnDate) throws InvalidIDException {
        return rentalService.returnRental(rentalId, returnDate);
    }
    
 // Calculate Late Fee
    @GetMapping("/{rentalId}/late-fee")
    public double calculateLateFee(@PathVariable int rentalId) throws InvalidIDException {
        return rentalService.calculateLateFee(rentalId);
    }
	
	

}
