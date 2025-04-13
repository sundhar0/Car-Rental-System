package com.api.carrental.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.carrental.Repository.CarAvailabilityRepository;
import com.api.carrental.model.CarAvailability;

@Service
public class CarAvailabilityService {

    @Autowired
    private CarAvailabilityRepository carAvailabilityRepository;

    // Save car availability
    public CarAvailability saveCarAvailability(CarAvailability carAvailability) {
        return carAvailabilityRepository.save(carAvailability);
    }

    // Get all car availabilities
    public List<CarAvailability> getAllCarAvailabilities() {
        return carAvailabilityRepository.findAll();
    }

    // Get car availability by ID
    public CarAvailability getCarAvailabilityById(Long id) {
        return carAvailabilityRepository.findById(id).orElse(null);
    }

    // Update car availability
    public CarAvailability updateCarAvailability(Long id, CarAvailability carAvailability) {
        carAvailability.setAvailabilityId(id);
        return carAvailabilityRepository.save(carAvailability);
    }

    // Delete car availability
    public void deleteCarAvailability(Long id) {
        carAvailabilityRepository.deleteById(id);
    }
}
