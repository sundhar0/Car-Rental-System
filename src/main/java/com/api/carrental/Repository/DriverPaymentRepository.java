package com.api.carrental.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.carrental.model.DriverPayment;

public interface DriverPaymentRepository extends JpaRepository<DriverPayment, Integer>{

	List<DriverPayment> findByDriverName(String driverName);


	DriverPayment findByDriver_DriverId(int driverId);



	List<DriverPayment> findAllByPaymentDate(LocalDate date);
	
}
