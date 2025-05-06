package com.api.carrental.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Repository.CarApprovalRepository;
import com.api.carrental.Repository.CarRepository;
import com.api.carrental.Service.AuthService;
import com.api.carrental.Service.CarService;
import com.api.carrental.Service.CustomerService;
import com.api.carrental.enums.CarSaleType;
import com.api.carrental.model.Car;
import com.api.carrental.model.CarApproval;
import com.api.carrental.model.User;

@RestController
@CrossOrigin(origins = {"http://localhost:5173/"})
@RequestMapping("/api/car")
public class CarController {
	
	@Autowired
	private CarService carService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private AuthService authservice;
	@Autowired
	private CarApprovalRepository carApprovalRepository;
	@Autowired
	private CarRepository carRepository;
	
	@PostMapping("/add/{ownId}")	
	public Car add(@PathVariable int ownId, @RequestBody Car car) throws InvalidIDException {
		User user = authservice.getById(ownId);
		car.setCarOwner(user);
		return carService.add(car);
	}
	
	public Page<Car> getAll(Pageable pageable) {
	    // First get approved cars that are for sale
	    List<CarApproval> approvedApprovals = carApprovalRepository.findByApprovedTrue();
	    List<Car> approvedCars = approvedApprovals.stream()
	            .filter(ca -> ca.getCar().getCarSaleType() == CarSaleType.SELL)
	            .map(CarApproval::getCar)
	            .toList();
	    
	    // Convert to page
	    int start = (int) pageable.getOffset();
	    int end = Math.min((start + pageable.getPageSize()), approvedCars.size());
	    
	    return new PageImpl<>(
	            approvedCars.subList(start, end),
	            pageable,
	            approvedCars.size()
	    );
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
	
	public Car uploadImage(MultipartFile file,int cid) throws IOException, InvalidIDException {
		/*check if cid isvalid */
		Car car = carRepository.findById(cid)
				.orElseThrow(()->new InvalidIDException("Invalid cID given.."));
		
		List<String> allowedExtensions = Arrays.asList("png","jpg","jpeg","gif","svg"); 
		String originalFileName = file.getOriginalFilename(); 
		System.out.println(originalFileName);
		String extension= originalFileName.split("\\.")[1];
		/*Check weather extension is allowed or not */
		if( !(allowedExtensions.contains(extension))) {
			throw new RuntimeException("Image Type Invalid");
		}		
		String uploadPath= "D:\\";
		
		/*Create directory *///Check if directory is present else create it
		Files.createDirectories(Paths.get(uploadPath));
		/*Define full path with folder and image name */
		Path path = Paths.get(uploadPath + "\\" +originalFileName); 
		/*Copy the image into uploads path */
		Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		/*Save this path in Db */
		car.setCarImage(path.toString());
		return carRepository.save(car);
	}
}
