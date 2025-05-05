package com.api.carrental.Service;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.carrental.Exception.DriverNotAvailable;
import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Repository.DriverScheduleRepository;
import com.api.carrental.model.Driver;
import com.api.carrental.model.DriverSchedule;


@Service
public class DriverScheduleService {
	
	@Autowired
	private DriverScheduleRepository driverScheduleRepository;

	@Autowired
	private DriverScheduleRepository scheduleRepository;
	Logger logger=LoggerFactory.getLogger("DriverScheduleService");
	//Adding the driver schedule
	//checking the schedule of the driver already exist or not
	//not exist means add the driver schedule
	public DriverSchedule addOrUpdateByDriverId(int driverId, DriverSchedule driverSchedule) {
	    DriverSchedule existingSchedule = driverScheduleRepository.findByDriver_driverId(driverId);
	    
	    if (existingSchedule != null) {
	        existingSchedule.setAvailableFrom(driverSchedule.getAvailableFrom());
	        existingSchedule.setAvailableTo(driverSchedule.getAvailableTo());
	        existingSchedule.setDriver(driverSchedule.getDriver());
	        logger.info("Driver Scheduled updated");
	        return driverScheduleRepository.save(existingSchedule);
	    } else {
	    	logger.info("Driver Schedule Added");
	        return driverScheduleRepository.save(driverSchedule);
	    }
	   
	}
	//getting the drivers from a particular range
	//getting the range
	//filtering the drivers from particular range
	public List<Driver> getAvailableDriversOn(LocalDate dateFrom, LocalDate dateTo) throws DriverNotAvailable {
	    List<DriverSchedule> schedules = scheduleRepository
	        .findByAvailableFromLessThanEqualAndAvailableToGreaterThanEqual(dateFrom, dateTo);
	    if (schedules.isEmpty()) 
	        throw new DriverNotAvailable("No driver available on that day");
	    
	    return schedules.stream()
	        .map(ds->ds.getDriver())
	        .distinct().toList();
	}
	//getting all the drivers
	
	public List<DriverSchedule> getAll() {
		return driverScheduleRepository.findAll();
	}
	//getting the by their id
	public DriverSchedule getById(int driverScheId) throws InvalidIDException {
		Optional<DriverSchedule> opt = driverScheduleRepository.findById(driverScheId);
		if(opt.isEmpty())
			throw new InvalidIDException("DriverSchedule Id not Found");
		return opt.get();
	}
	//get by the driver userName
	public DriverSchedule getByDriverUsername(String username) {
		Optional<DriverSchedule> opt = driverScheduleRepository.findByDriverUserUsername(username);
		return opt.get();
	}
	//deleting the driver schedule
	//fetching the schedule id and deleting from the driver
	public void deleteDriverSchedule(int scheduleId) throws InvalidIDException {
        Optional<DriverSchedule> optionalSchedule = driverScheduleRepository.findById(scheduleId);
        if (optionalSchedule.isEmpty()) {
            throw new InvalidIDException("Driver Schedule not found");
        }
        logger.info("Driver Schedule Deleted...");
        driverScheduleRepository.deleteById(scheduleId);
    }
}
