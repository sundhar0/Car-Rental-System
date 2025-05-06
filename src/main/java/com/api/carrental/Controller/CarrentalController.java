package com.api.carrental.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.carrental.Exception.InvalidFuelException;
import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Exception.InvalidModelException;
import com.api.carrental.Exception.InvalidYearException;
import com.api.carrental.Service.CarrentalService;
import com.api.carrental.dto.MessageResponseDto;
import com.api.carrental.model.Car;

@RestController
@RequestMapping("/api/car/")
public class CarrentalController {
	@Autowired
	private CarrentalService carrentalService;
	@Autowired 
	private MessageResponseDto messageDto;
	
	@GetMapping("/getall")
 	public List<Car> getAllCar() {
 		return carrentalService.getAllCar();
 	}
	
	@GetMapping("/model/{model}")
	public ResponseEntity<?> getCarByModel(@PathVariable String model) {
	    try {
	        List<Car> list = carrentalService.getCarByModel(model);
	        return ResponseEntity.ok(list);
	    } catch (InvalidModelException e) {
	        messageDto.setBody(e.getMessage());
	        messageDto.setStatusCode(400); 
	        return ResponseEntity.status(400).body(messageDto);
	    }
	}
	
	@GetMapping("/year/{year}")
	public ResponseEntity<?> getCarByYear(@PathVariable String year) {
	    MessageResponseDto messageDto = new MessageResponseDto();
	    try {
	        List<Car> list = carrentalService.getCarByYear(year);
	        return ResponseEntity.ok(list);
	    } catch (InvalidYearException e) {
	        messageDto.setBody(e.getMessage());
	        messageDto.setStatusCode(400);
	        return ResponseEntity.status(400).body(messageDto);
	    }
	    
	}

	
	@GetMapping("/fuel/{fuel}")
	public ResponseEntity<?> getCarByFuel(@PathVariable("fuel") String fuelType) {
	    try {
	        List<Car> list = carrentalService.getCarByFuel(fuelType);
	        return ResponseEntity.ok(list);
	    } catch (InvalidFuelException e) {
	        MessageResponseDto messageDto = new MessageResponseDto();
	        messageDto.setBody(e.getMessage());
	        messageDto.setStatusCode(400); 
	        return ResponseEntity.status(400).body(messageDto);
	    }    
	}
	
	@GetMapping("/{carId}")
	public ResponseEntity<?>getCarById(@PathVariable int carId){
		try {
			Car car=carrentalService.getCarById(carId);
			return ResponseEntity.ok(car);
		}catch(InvalidIDException e) {
			messageDto.setBody(e.getMessage());
            messageDto.setStatusCode(400);
            return ResponseEntity.status(400).body(messageDto);
			
		}
	}
	
	// Search Car by Brand
	@GetMapping("/brand/{brand}")
	public ResponseEntity<?> getCarByBrand(@PathVariable String brand) {
	    try {
	        List<Car> list = carrentalService.getCarByBrand(brand);
	        return ResponseEntity.ok(list);
	    } catch (Exception e) {
	        messageDto.setBody(e.getMessage());
	        messageDto.setStatusCode(400);
	        return ResponseEntity.status(400).body(messageDto);
	    }
	}




}
