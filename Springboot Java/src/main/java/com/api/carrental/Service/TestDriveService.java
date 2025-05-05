package com.api.carrental.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Repository.AuthRepository;
import com.api.carrental.Repository.CarRepository;
import com.api.carrental.Repository.CustomerRepository;
import com.api.carrental.Repository.TestDriveRepository;
import com.api.carrental.model.Car;
import com.api.carrental.model.Customer;
import com.api.carrental.model.TestDrive;
import com.api.carrental.model.User;

@Service
public class TestDriveService {
    
    
    
    @Autowired
    private TestDriveRepository testDriveRepository;
    
    @Autowired
    private CarRepository carRepository;
    
    
    @Autowired
    private AuthRepository userRepository;

    public TestDrive addBooking(int carId, int userId, TestDrive testDrive) throws InvalidIDException {
        // Get user with proper error handling
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new InvalidIDException("User with ID " + userId + " not found"));
        
        // Get car with proper error handling
        Car car = carRepository.findById(carId)
            .orElseThrow(() -> new InvalidIDException("Car with ID " + carId + " not found"));
        
        // Set relationships
        testDrive.setCar(car);
        testDrive.setUser(user);
        
        // Save the test drive booking
        return testDriveRepository.save(testDrive);
    }

    public List<TestDrive> getAllBookings() {
        return testDriveRepository.findAll();
    }

    public Optional<TestDrive> getBookingsByCarId(int carId) throws InvalidIDException {
        // Verify car exists first
        if (!carRepository.existsById(carId)) {
            throw new InvalidIDException("Car with ID " + carId + " not found");
        }
        return testDriveRepository.findById(carId);
    }

    public Optional<TestDrive> getBookingsByUserId(int userId) throws InvalidIDException {
        // Verify user exists first
        if (!userRepository.existsById(userId)) {
            throw new InvalidIDException("User with ID " + userId + " not found");
        }
        return testDriveRepository.findById(userId);
    }

    public TestDrive getBookingById(int tdId) throws InvalidIDException {
        return testDriveRepository.findById(tdId)
            .orElseThrow(() -> new InvalidIDException("TestDrive with ID " + tdId + " not found"));
    }

	public TestDrive findById(int tdId) {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}