package com.api.carrental.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.carrental.model.DriverSchedule;

public interface DriverScheduleRepository extends JpaRepository<DriverSchedule, Integer>{

	List<DriverSchedule> findByAvailableFromLessThanEqualAndAvailableToGreaterThanEqual(LocalDate date,
			LocalDate date2);

    DriverSchedule findByDriver_driverId(int driverId);

}
