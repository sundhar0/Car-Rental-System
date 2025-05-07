package com.api.carrental.Repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import com.api.carrental.enums.DriverAvailability;
import com.api.carrental.model.Driver;

public interface DriverRepository extends JpaRepository<Driver, Integer>{

	Driver findByLicenseNo(String licenseNo);

	Driver findByUserUsername(String username);

	List<Driver> findByDriverAvailability(DriverAvailability available);

	Driver findByDriverId(int driverId);

}
