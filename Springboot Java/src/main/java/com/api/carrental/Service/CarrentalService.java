package com.api.carrental.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.carrental.Exception.InvalidFuelException;
import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Exception.InvalidModelException;
import com.api.carrental.Exception.InvalidYearException;
import com.api.carrental.Repository.CarrentalRepository;
import com.api.carrental.model.Car;

@Service
public class CarrentalService {
	@Autowired
	private CarrentalRepository carrentalRepository;

	public List<Car> getCarByModel(String model) throws InvalidModelException {
		List<Car>car=carrentalRepository.findByModel(model);
		if(car.isEmpty()) {
			throw new InvalidModelException("Car model '" + model + "' not found in database.");
		}
		return car;
	}

	public List<Car> getCarByYear(String year) throws InvalidYearException {
		if (year.length() != 4 || !year.matches("\\d{4}"))
			throw new InvalidYearException("No Such Year Exist.");
		return carrentalRepository.findByYear(year);
	}

	public List<Car> getCarByFuel(String fuelType) throws InvalidFuelException {
	    List<Car> car = carrentalRepository.findCarsByFuelType(fuelType);
	    if (car.isEmpty()) {
	        throw new InvalidFuelException("Fuel not found in Database");
	    }
	    return car;
	}

	public List<Car> getAllCar() {
		return carrentalRepository.findAll();
	}

	public Car getCarById(int carId) throws InvalidIDException {
		Optional<Car> optional=carrentalRepository.findById(carId);
		if(optional.isEmpty()) {
			throw new InvalidIDException("Car not found with id: " + carId);
		}
		return optional.get();
	}



}
