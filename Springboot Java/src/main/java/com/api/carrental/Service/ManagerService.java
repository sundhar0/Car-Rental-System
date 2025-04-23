package com.api.carrental.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Repository.ManagerRepository;
import com.api.carrental.model.Manager;

@Service
public class ManagerService {
	@Autowired
	private ManagerRepository managerRepository;

	public Manager addManager(Manager manager) {
		return managerRepository.save(manager);
	}

	public List<Manager> GetAllManager() {
		List<Manager> list=managerRepository.findAll();
		return list;
	}

	public Manager getById(int mId) throws InvalidIDException {
		Optional<Manager> optional=managerRepository.findById(mId);
		if(optional.isEmpty())
			throw new InvalidIDException("Manager Id given is Invalid...");
		return optional.get();
	}

}
