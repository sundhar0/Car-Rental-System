package com.api.carrental.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Repository.CarRepository;
import com.api.carrental.Repository.CustomerRepository;
import com.api.carrental.Repository.TestDriveRepository;
import com.api.carrental.model.Car;
import com.api.carrental.model.Customer;
import com.api.carrental.model.TestDrive;

@Service
public class TestDriveService {
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CarService carService;
	@Autowired
	private TestDriveRepository testDriveRepository;
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private CustomerRepository customerRepository;

	//this will be used to store the review from getting the car id and customer id
	public Object addBooking(int carId, Long cusId, TestDrive testDrive) throws InvalidIDException {
		//it will get the details from customer id
		Customer customer=customerService.getSingleCustomer(cusId);
		//it will get the details from car id
		Car car=carService.getById(carId);
		//setting the values in test drive
		testDrive.setCar(car);
		testDrive.setCustomer(customer);
		//added the values in table
		return testDriveRepository.save(testDrive);
	}

	public List<TestDrive> getAllBooking() {
		//this function will be used to view all the booking 
		return testDriveRepository.findAll();
	}

	public Object getByCarId(int carId) throws InvalidIDException {
		//this will be used to see all the bookings from car id
		Optional<Car> car=carRepository.findById(carId);
		if(car.get()==null)
			throw new InvalidIDException("Given Car id is Invalid");
		return car.get();
	}
	
	public Object getByCustomerId(Long cusId) throws InvalidIDException {
		// this will be used to show the values by customer id
		Optional<Customer> customer=customerRepository.findById(cusId);
		if(customer.get()==null)
			throw new InvalidIDException("Given Customer Id is Invalid");
		return customer.get();
	}

	public TestDrive findById(int tdId) throws InvalidIDException {
		Optional<TestDrive> testDrive=testDriveRepository.findById(tdId);
		if(testDrive.get()==null)
			throw new InvalidIDException("Given TestDrive Id is Invalid...");
		return testDrive.get();
	}

}
