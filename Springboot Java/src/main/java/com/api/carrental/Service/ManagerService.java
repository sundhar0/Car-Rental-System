package com.api.carrental.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Repository.ManagerRepository;
import com.api.carrental.model.Manager;

@Service
public class ManagerService {
	@Autowired
	private ManagerRepository managerRepository;

	Logger logger=LoggerFactory.getLogger("ManagerService");
	//creating manager
	//fetching the details of the manager from API
	//saving the details in manager table
	public Manager addManager(Manager manager) {
		logger.info("Manager Added Successfully");
		return managerRepository.save(manager);
	}
	//getting all the manager
	//use findAll method for fetching all the managers
	//return the values in list
	
	public List<Manager> GetAllManager() {
		List<Manager> list=managerRepository.findAll();
		return list;
	}
	//finding the single manager details
	//fetching the id of the manager
	//returning it into optional value
	public Manager getById(int mId) throws InvalidIDException {
		Optional<Manager> optional=managerRepository.findById(mId);
		if(optional.isEmpty())
			throw new InvalidIDException("Manager Id given is Invalid...");
		return optional.get();
	}

}
