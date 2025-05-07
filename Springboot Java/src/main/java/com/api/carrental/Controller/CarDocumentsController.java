package com.api.carrental.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.carrental.Service.CarDocumentsService;
import com.api.carrental.model.CarDocuments;

@RestController
@RequestMapping("/api/cars")
public class CarDocumentsController {

	@Autowired
	private CarDocumentsService carDocumentsService;
	
	@PostMapping("/addcarsandDocuments")
	public CarDocuments addcars(@RequestBody CarDocuments carDocuments) {
		return carDocumentsService.addCars(carDocuments);
	}
}
