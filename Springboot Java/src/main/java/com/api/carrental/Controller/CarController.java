package com.api.carrental.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Service.AuthService;
import com.api.carrental.Service.CarService;
import com.api.carrental.dto.CarDto;
import com.api.carrental.model.Car;
import com.api.carrental.model.User;

@RestController
@RequestMapping("/api/car")
public class CarController {
	
	@Autowired
	private CarService carService;
	
	@Autowired
	private AuthService authservice;
	
	@PostMapping("/add/{ownId}")	
	public Car add(@PathVariable int ownId, @RequestBody Car car) throws InvalidIDException {
		User user = authservice.getById(ownId);
		car.setCarOwner(user);
		return carService.add(car);
	}
	
	@GetMapping("/getAll")
	public CarDto getAllCars(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "10") int size) {
	    
	    Pageable pageable = PageRequest.of(page, size);
	    Page<Car> carPage = carService.getAll(pageable);
	    
	    CarDto dto = new CarDto();
	    dto.setList(carPage.getContent());
	    dto.setCurrentPage(page);
	    dto.setSize(size);
	    dto.setTotalElements((int) carPage.getTotalElements());
	    dto.setTotalPages(carPage.getTotalPages());
	    
	    return dto;
	}
	
	@GetMapping("/getById/{carId}")
	public Car getById(@PathVariable int carId) throws InvalidIDException {
		return carService.getById(carId);
	}
	
//	@GetMapping("/getReview/{cId}")
//	public Object SeeReview(@PathVariable Long cId) throws InvalidIDException {
//		return carService.getReview(cId);
//	}
	@GetMapping("/getHistory/{cId}")
	public Object getHistory(@PathVariable int cId) throws InvalidIDException {
		return carService.getHistory(cId);
	}
	@DeleteMapping("/delete/{cId}")
	public void deleteCar(@PathVariable int cId) throws InvalidIDException {
		carService.DeleteCar(cId);
	}
	@PutMapping("/updateCar/{cId}")
	public Object updateCar(@PathVariable int cId,@RequestBody Car newValue) throws InvalidIDException {
		return carService.updateCar(cId,newValue);
	}
	
	@PostMapping("/image/upload/{cid}")
	public Car uploadImage(@PathVariable int cid, 
							@RequestParam MultipartFile file) throws IOException, InvalidIDException {
		
		return carService.uploadImage(file,cid);
	}
}
