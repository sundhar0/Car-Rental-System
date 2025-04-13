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
		
		//It will go and get the cars which are approved by the manager and stored in approved variables
		List<CarApproval> approvedApprovals = carApprovalRepository.findByApprovedTrue();
		//this stream will be used to get the sales type which are in "sell"
		//because we are showing the cars the which are comes for sell
	    return approvedApprovals.stream()
	    						.filter(ca->ca.getCar().getCarSaleType() == CarSaleType.SELL)
	    						.map(ca->ca.getCar())
	                            .toList();
	}

	public Object getByModel(String model) {
		//we are using the previous method for collecting the cars which come for sell
		List<Car> approved = this.getAll();
		//this will be used to show the cars which are the model selected by the customer
		return approved.parallelStream()
				.filter(ca->ca.getModel()==model)
                .toList();
	}

	public Object getByYear(String year) {
		//we are using the previous method for collecting the cars which come for sell
		List<Car> approved=this.getAll();
		//this will be used to show the cars which are the year selected by the customer
		return approved.parallelStream()
				.filter(ca->ca.getYear()==year)
				.toList();

		}

	public Object getByFuelType(String ft) {
		//we are using the previous method for collecting the cars which come for sell
		List<Car> approved=this.getAll();
		//this will be used to show the cars which are the fuel type like petrol or diesel selected by the customer
		return approved.parallelStream()
				.filter(ca->ca.getFuelType()==ft)
				.toList();
	}

	public Object getByPrice(double amount) {
		//we are using the previous method for collecting the cars which come for sell
		List<Car> approved=this.getAll();
		//this will be used to show the cars which are the amount within a range selected by the customer
		return approved.parallelStream()
				.filter(ca->ca.getPrice()==amount)
				.toList();
	}

}
