package com.api.carrental.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.carrental.Repository.DriverScheduleRepository;
import com.api.carrental.model.DriverSchedule;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DriverScheduleService {
	
	@Autowired
	private DriverScheduleRepository driverScheduleRepository;
	

	public DriverSchedule addOrUpdateByDriverId(int driverId, DriverSchedule driverSchedule) {
	    DriverSchedule existingSchedule = driverScheduleRepository.findByDriver_driverId(driverId);
	    
	    if (existingSchedule != null) {
	        existingSchedule.setAvailableFrom(driverSchedule.getAvailableFrom());
	        existingSchedule.setAvailableTo(driverSchedule.getAvailableTo());
	        existingSchedule.setDriver(driverSchedule.getDriver());

	        return driverScheduleRepository.save(existingSchedule);
	    } else {
	        return driverScheduleRepository.save(driverSchedule);
	    }
	}
}
