package com.api.carrental.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.carrental.Repository.CarDocumentsRepository;
import com.api.carrental.Repository.CarRepository;
import com.api.carrental.model.Car;
import com.api.carrental.model.CarDocuments;

@Service
public class CarDocumentsService {
	@Autowired
	private CarDocumentsRepository carDocumnetsRepository;
	@Autowired
	private CarRepository carRepository;

	public CarDocuments addCars(CarDocuments carDocuments) {
		Car car=carDocuments.getCar();
		carRepository.save(car);
		carDocuments.setCar(car);
		return carDocumnetsRepository.save(carDocuments);
	}

}
