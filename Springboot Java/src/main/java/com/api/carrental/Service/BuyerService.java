package com.api.carrental.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.carrental.Exception.InvalidFuelException;
import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Exception.InvalidModelException;
import com.api.carrental.Exception.InvalidPriceException;
import com.api.carrental.Exception.InvalidYearException;
import com.api.carrental.Repository.CarApprovalRepository;
import com.api.carrental.enums.CarSaleType;
import com.api.carrental.model.Car;
import com.api.carrental.model.CarApproval;

@Service
public class BuyerService {
	@Autowired
	private CarApprovalRepository carApprovalRepository;
	@Autowired
	private CarService carService;
	

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

	public Object getByModel(String model) throws InvalidModelException {
		//we are using the previous method for collecting the cars which come for sell
		List<Car> approved = this.getAll();
		//this will be used to show the cars which are the model selected by the customer
		
		List<Car> buyer=approved.parallelStream()
				.filter(ca->ca.getModel()==model)
                .toList();
		if(buyer.isEmpty())
			throw new InvalidModelException("Given model has no data in it....");
		return buyer;
	}

	public Object getByYear(String year) throws InvalidYearException {
		//we are using the previous method for collecting the cars which come for sell
		List<Car> approved=this.getAll();
		//this will be used to show the cars which are the year selected by the customer
		
		List<Car> buyer=approved.parallelStream()
				.filter(ca->ca.getYear()==year)
				.toList();
		if(approved.isEmpty())
			throw new InvalidYearException("Given year has no data in it....");
		return buyer;

		}

	public Object getByFuelType(String ft) throws InvalidFuelException {
		//we are using the previous method for collecting the cars which come for sell
		List<Car> approved=this.getAll();
		//this will be used to show the cars which are the fuel type like petrol or diesel selected by the customer
		List<Car>buyer= approved.parallelStream()
				.filter(ca->ca.getFuelType()==ft)
				.toList();
		if(approved.isEmpty())
			throw new InvalidFuelException("Given Fuel Type has no data in it....");
		return buyer;
	}

	public Object getByPrice(double amount) throws InvalidPriceException {
		//we are using the previous method for collecting the cars which come for sell
		List<Car> approved=this.getAll();
		//this will be used to show the cars which are the amount within a range selected by the customer
		List<Car> buyer= approved.parallelStream()
				.filter(ca->ca.getPrice()==amount)
				.toList();
		if(approved.isEmpty())
			throw new InvalidPriceException("Given Price has no data in it....");
		return buyer;
	}


	public Object getbyCarId(int carId) throws InvalidIDException {
		// TODO Auto-generated method stub
		Car car=carService.getById(carId);
		if(car==null)
			throw new InvalidIDException("Given Id is Invalid...");
		return car;
	}

}
