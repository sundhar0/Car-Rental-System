package com.api.carrental.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Repository.RentalRepository;
import com.api.carrental.enums.RentalStatus;
import com.api.carrental.model.Customer;
import com.api.carrental.model.Rental;

@Service
public class RentalService {
	@Autowired
	private RentalRepository rentalRepository;

	@Autowired
    private CustomerService customerService;

	public Rental saveRental(Rental rental) {
	    Customer customer = rental.getCustomer();
	    if (customer != null && customer.getId() == 0) {
	        int userId = customer.getUser().getUserId(); //  Get userId from customer
	        customerService.saveCustomer(customer);
	    }

	    return rentalRepository.save(rental);
	}


    public Rental updateRentalStatus(int rentalId, RentalStatus status) throws InvalidIDException {
        Rental rental = getRentalById(rentalId);
        rental.setStatus(status);
        return rentalRepository.save(rental);
    }

    public Rental getRentalById(int rentalId) throws InvalidIDException {
        return rentalRepository.findById(rentalId)
                .orElseThrow(() -> new InvalidIDException("Rental not found with ID: " + rentalId));
    }

    public Rental returnRental(int rentalId, String returnDate) throws InvalidIDException {
        Rental rental = getRentalById(rentalId);
        rental.setActualReturnDate(returnDate);

        LocalDate expected = LocalDate.parse(rental.getExpectedReturnDate());
        LocalDate actual = LocalDate.parse(returnDate);

        long daysLate = ChronoUnit.DAYS.between(expected, actual);
        if (daysLate > 0) {
            rental.setLateFee(daysLate * 100); // example: 100 per day late
            rental.setStatus(RentalStatus.LATE);
        } else {
            rental.setLateFee(0);
            rental.setStatus(RentalStatus.RETURNED);
        }

        return rentalRepository.save(rental);
    }

    public double calculateLateFee(int rentalId) throws InvalidIDException {
        Rental rental = getRentalById(rentalId);
        if (rental.getLateFee() == 0 && rental.getActualReturnDate() != null) {
            // Recalculate just in case
            LocalDate expected = LocalDate.parse(rental.getExpectedReturnDate());
            LocalDate actual = LocalDate.parse(rental.getActualReturnDate());

            long daysLate = ChronoUnit.DAYS.between(expected, actual);
            if (daysLate > 0) {
                rental.setLateFee(daysLate * 100);
                rentalRepository.save(rental);
            }
        }
        return rental.getLateFee();
    }

}
