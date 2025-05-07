package com.api.carrental.Repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.carrental.model.Car;

public interface CarRepository extends JpaRepository<Car, Integer> {


	List<Car> findByCarOwnerUserId(int userId);

	Object findByCustomerId(int i);

}
