package com.api.carrental.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.carrental.Repository.CarApprovalRepository;
import com.api.carrental.model.CarApproval;

@Service
public class CarApprovalService {
	
	@Autowired
	private CarApprovalRepository carApprovalRepository;

	public CarApproval add(CarApproval carApproval) {
		return carApprovalRepository.save(carApproval);
	}
	
}
