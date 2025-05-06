package com.api.carrental.Repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.carrental.model.Car;

public interface CarRepository extends JpaRepository<Car, Integer> {

<<<<<<< HEAD:src/main/java/com/api/carrental/Repository/CarRepository.java
	List<Car> findByStatus(String string);

	

}
=======

	List<Car> findByCarOwnerUserId(int userId);

}
>>>>>>> 1a6859e55dacab412dbafd30a213695588396b9d:Springboot Java/src/main/java/com/api/carrental/Repository/CarRepository.java
