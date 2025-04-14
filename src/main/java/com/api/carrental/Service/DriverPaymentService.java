package com.api.carrental.Service;



import java.time.LocalDate;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Exception.InvalidPaymentException;
import com.api.carrental.Repository.DriverPaymentRepository;
import com.api.carrental.model.Driver;
import com.api.carrental.model.DriverPayment;
import com.api.carrental.model.Manager;

@Service
public class DriverPaymentService {
	
	@Autowired
	private DriverService driverService;
	
	
	@Autowired
	private ManagerService managerService;
	
	@Autowired
	private DriverPaymentRepository driverPaymentRepository;
	
	private DriverPaymentService driverPaymentService;

	public DriverPayment add(DriverPayment driverPayment,int managerId,int driverId) throws InvalidIDException {
		Driver driver = driverService.getById(driverId);
		driverPayment.setDriver(driver);
		Manager manager = managerService.getById(managerId);
		driverPayment.setUpdatedBy(manager);
		driverPayment.setPaymentDate(LocalDate.now());
		
		return driverPaymentRepository.save(driverPayment);
	}

	public List<DriverPayment> getAll() {
		return driverPaymentRepository.findAll();
	}

	public DriverPayment getById(int driverPayId) throws InvalidIDException {
		Optional<DriverPayment> opt = driverPaymentRepository.findById(driverPayId);
		if(opt.isEmpty())
			throw new InvalidIDException("DriverPayment was not found");
		return opt.get();
	}

	public List<DriverPayment> getByDriverName(String driverName) throws InvalidPaymentException {
		List<DriverPayment> list = driverPaymentRepository.findByDriverName(driverName);
		if(list == null) 
			throw new InvalidPaymentException("Driver not found");
		return list;
	}

	public DriverPayment getByDriverId(int driverId) throws InvalidPaymentException {
		DriverPayment driverPayment = driverPaymentRepository.findByDriver_DriverId(driverId);
		if(driverPayment == null)
			throw new InvalidPaymentException("Driver not found");
		return driverPayment;
	}

	public List<DriverPayment> getByDate(LocalDate date) throws InvalidPaymentException {
		List<DriverPayment> driverPayment = driverPaymentRepository.findAllByPaymentDate(date);
		if(driverPayment == null)
			throw new InvalidPaymentException("No data found in this date");
		return driverPayment;
	}

	public String updatePayment(double payment, int driverId,
			int managerId) throws InvalidPaymentException, InvalidIDException {
		DriverPayment driverPayment = driverPaymentService.getByDriverId(driverId);
		driverPayment.setAmount(payment);
		Manager manager = managerService.getById(managerId);
		driverPayment.setUpdatedBy(manager);
		driverPaymentRepository.save(driverPayment);
		return "Payment Updated sucessfully";
	}
	
	public void deleteDriverPayment(int paymentId) throws InvalidIDException {
        Optional<DriverPayment> optionalDriverPayment = driverPaymentRepository.findById(paymentId);
        if (optionalDriverPayment.isEmpty()) {
            throw new InvalidIDException("Driver Payment not found");
        }
        driverPaymentRepository.deleteById(paymentId);
    }
}
