package com.api.carrental.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.carrental.Repository.CarApprovalRepository;
import com.api.carrental.enums.CarSaleType;
import com.api.carrental.model.Car;
import com.api.carrental.model.CarApproval;

@Service
public class BuyerService {
	@Autowired
	private CarApprovalRepository carApprovalRepository;
	
	

	public List<Car> getAll() {
		List<CarApproval> approvedApprovals = carApprovalRepository.findByApprovedTrue();
	    return approvedApprovals.stream()
	    						.filter(ca->ca.getCar().getCarSaleType() == CarSaleType.SELL)
	    						.map(ca->ca.getCar())
	                            .toList();
	}

	public Object getByModel(String model) {
		List<Car> approved = this.getAll();
		return approved.parallelStream()
				.filter(ca->ca.getModel()==model)
                .toList();
	}

	public Object getByYear(String year) {
		List<Car> approved=this.getAll();
		return approved.parallelStream()
				.filter(ca->ca.getYear()==year)
				.toList();

		}

	public Object getByFuelType(String ft) {
		List<Car> approved=this.getAll();
		return approved.parallelStream()
				.filter(ca->ca.getFuelType()==ft)
				.toList();
	}

}
