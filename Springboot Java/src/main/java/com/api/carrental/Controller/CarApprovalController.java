package com.api.carrental.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Service.CarApprovalService;
import com.api.carrental.Service.CarService;
import com.api.carrental.Service.ManagerService;
import com.api.carrental.model.Car;
import com.api.carrental.enums.CarStatus;
import com.api.carrental.model.CarApproval;
import com.api.carrental.model.Manager;

@RestController
@RequestMapping("/api/carapproval")
public class CarApprovalController {
	
	@Autowired
	private ManagerService managerService;
	
	@Autowired
	private CarService carService;
	
	@Autowired
	private CarApprovalService carApprovalService;
	
	
	@PostMapping("/add/{carId}/{managerId}")
	public CarApproval add(@RequestBody CarApproval carApproval,
			@PathVariable int carId, @PathVariable int managerId) throws InvalidIDException {
		Manager manager = managerService.getById(managerId);
		Car car = carService.getById(carId);
		carApproval.setCar(car);
		carApproval.setManager(manager);
		if(carApproval.isApproved()) {
			car.setStatus(CarStatus.APPROVED);
		}else {
			car.setStatus(CarStatus.REJECTED);
		}
		carApproval = carApprovalService.add(carApproval);
		
		
		return carApproval;	
	}
}
