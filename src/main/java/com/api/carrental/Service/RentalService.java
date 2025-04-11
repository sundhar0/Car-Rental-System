package com.api.carrental.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.carrental.Repository.RentalHistoryRepository;
import com.api.carrental.Repository.RentalRepository;
import com.api.carrental.model.Rental;
import com.api.carrental.model.RentalHistory;

@Service
public class RentalService {
	
	@Autowired
	private RentalRepository rentalrepository;
	
	
	@Autowired
	private RentalHistoryRepository rentalHistoryRepository;
	
	public Rental add(Rental rental) {
		Rental savedRental = rentalrepository.save(rental);
		RentalHistory rentalHistory = new RentalHistory();
		rentalHistory.setRental(savedRental);
		rentalHistory.setCompletedDate(savedRental.getEndDate());
		rentalHistory.setUser(savedRental.getUser());
		rentalHistoryRepository.save(rentalHistory);
		
		return savedRental;
		
	}
}
