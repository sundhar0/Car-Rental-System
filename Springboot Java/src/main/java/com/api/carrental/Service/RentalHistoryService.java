package com.api.carrental.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Repository.RentalHistoryRepository;
import com.api.carrental.model.RentalHistory;
@Service
public class RentalHistoryService {
	
	@Autowired
    private RentalHistoryRepository rentalHistoryRepository;

	public RentalHistory addRentalHistory(RentalHistory rentalHistory) {
		return rentalHistoryRepository.save(rentalHistory);
	}
	
	public List<RentalHistory> getAllRentalHistories() {
        return rentalHistoryRepository.findAll();
    }

	public void deleteRentalHistory(int id) throws InvalidIDException {
		RentalHistory history = getRentalHistoryById(id);
        rentalHistoryRepository.delete(history);
		
	}

	private RentalHistory getRentalHistoryById(int id) throws InvalidIDException {
		 return rentalHistoryRepository.findById(id)
		            .orElseThrow(() -> new InvalidIDException("RentalHistory with ID " + id + " not found"));
	}

}
