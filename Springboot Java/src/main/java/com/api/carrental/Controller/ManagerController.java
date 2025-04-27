package com.api.carrental.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.api.carrental.Service.ManagerService;
import com.api.carrental.model.Manager;

@RestController
@RequestMapping("/api/Manager")
public class ManagerController {

	@Autowired
	private ManagerService managerService;
	
	@PostMapping("/add")
	public Manager addManager(@RequestBody Manager manager) {
		return managerService.addManager(manager);
	}
	
	@GetMapping("/getAll")
	public List<Manager> GetAllManager() {
		return managerService.GetAllManager();
	}
}