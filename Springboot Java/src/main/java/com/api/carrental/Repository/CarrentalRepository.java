package com.api.carrental.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.carrental.model.Car;

public interface CarrentalRepository extends JpaRepository<Car,Integer>{

	List<Car> findByModel(String model);

	List<Car> findByYear(String year);


	List<Car> findCarsByFuelType(String fuelType);

	

	

}
