package com.api.carrental.Service;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

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
	
	public List<Driver> getAvailableDriversOn(LocalDate dateFrom, LocalDate dateTo) throws DriverNotAvailable {
	    List<DriverSchedule> schedules = scheduleRepository
	        .findByAvailableFromLessThanEqualAndAvailableToGreaterThanEqual(dateFrom, dateTo);
	    if (schedules.isEmpty()) 
	        throw new DriverNotAvailable("No driver available on that day");
	    
	    return schedules.stream()
	        .map(ds->ds.getDriver())
	        .distinct().toList();
	}

	public List<DriverSchedule> getAll() {
		return driverScheduleRepository.findAll();
	}

	public DriverSchedule getById(int driverScheId) throws InvalidIDException {
		Optional<DriverSchedule> opt = driverScheduleRepository.findById(driverScheId);
		if(opt.isEmpty())
			throw new InvalidIDException("DriverSchedule Id not Found");
		return opt.get();
	}

	public DriverSchedule getByDriverUsername(String username) {
		Optional<DriverSchedule> opt = driverScheduleRepository.findByDriverUserUsername(username);
		return opt.get();
	}
	
	public void deleteDriverSchedule(int scheduleId) throws InvalidIDException {
        Optional<DriverSchedule> optionalSchedule = driverScheduleRepository.findById(scheduleId);
        if (optionalSchedule.isEmpty()) {
            throw new InvalidIDException("Driver Schedule not found");
        }
        driverScheduleRepository.deleteById(scheduleId);
    }
}
