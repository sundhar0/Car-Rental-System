package com.api.carrental.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.carrental.Repository.CarTransactionRepository;
import com.api.carrental.model.CarTransaction;

@Service
public class CarTransactionService {
	
	@Autowired
	private CarTransactionRepository ctRepository;

	public CarTransaction buy(CarTransaction ct) {
		return ctRepository.save(ct);
	}
	
	
}
