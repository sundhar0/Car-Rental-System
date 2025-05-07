package com.api.carrental.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Repository.CarApprovalRepository;
import com.api.carrental.Repository.CarRepository;
import com.api.carrental.model.Car;
import com.api.carrental.model.CarApproval;
import com.api.carrental.model.User;
import jakarta.transaction.Transactional;
import com.api.carrental.enums.CarSaleType;
import com.api.carrental.enums.CarStatus;

@Service
public class CarService {
	
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private CarApprovalRepository carApprovalRepository;
//	@Autowired
//	private ReviewFeedbackService reviewFeedbackService;
	@Autowired
	private AuthService authService;
	
	@Autowired
	private TestDriveService testDriveService;
	
	Logger logger=LoggerFactory.getLogger("CarService");
	
	public Car add(Car car) {
		//this method will store the car status will defaultly in pending
		// after the manager given the approval it changes to approved
		car.setStatus(CarStatus.PENDING);
		//it will save the cars in the car table
		logger.info("Car Added...");
	    return carRepository.save(car);
	}

	public Car getById(int carId) throws InvalidIDException {
		// this will be helpful to find the cars in a particular car id
		Optional<Car> opt = carRepository.findById(carId);
		if(opt.get()==null)
			throw new InvalidIDException("Given Id is Invalid....");

		return opt.get();
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


//	public Object getReview(Long cId) throws InvalidIDException {
//		//this will be used to show the feedback about the customer
//		//after getting the customer id we are checking the details about the cutomer in the reviewfeedback table
//		List<ReviewFeedback> list=reviewFeedbackService.getByReview(cId);
//		if(list.isEmpty())
//			throw new InvalidIDException("Given Customer Id is Invalid...");
//		return list;
//	}
	public List<Car> getHistory(int cId) throws InvalidIDException {
		//it will get the history by customer id
		User user = authService.getById(cId);
		if(user==null)
			throw new InvalidIDException("Given Customer Id is Inavlid...");
		return carRepository.findByCarOwnerUserId(cId);

	}
	@Transactional
	public void deleteCar(int cId) throws InvalidIDException {
	    Optional<Car> optional = carRepository.findById(cId);
	    if (optional.isEmpty())
	        throw new InvalidIDException("Given Car Id is Invalid!!");
	    
	    testDriveService.deleteByCarID(cId); 
	    carRepository.deleteById(cId); 
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
		logger.info("Image uploaded...");
		return carRepository.save(car);
	}

	public Page<Car> getAllCarsById(int id, Pageable pageable) {
		// First get approved cars that are for sale
	    List<CarApproval> approvedApprovals = carApprovalRepository.findByApprovedTrueAndId(id);
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
	public Object updateCar(int cId, Car newValue) throws InvalidIDException {
		Optional<Car> optional=carRepository.findById(cId);
		if(optional==null) {
			throw new InvalidIDException("Given Id is Invalid");
		}
		Car newCar=optional.get();
		newCar.setCarModel(newValue.getCarModel());
		newCar.setCarMake(newValue.getCarMake());
		newCar.setYear(newValue.getYear());
		newCar.setLicensePlateNumber(newValue.getLicensePlateNumber());
		newCar.setVehicleRegistrationNumber(newValue.getVehicleRegistrationNumber());
		newCar.setCarColor(newValue.getCarColor());
		logger.info("Car Value Updated..");
		logger.info("Car Details updated...");
		return carRepository.save(newCar);
	}
	
}
