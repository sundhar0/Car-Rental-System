package com.api.carrental.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.carrental.Service.CarAvailabilityService;
import com.api.carrental.model.CarAvailability;

@RestController
@RequestMapping("/api/caravailability")
public class CarAvailabilityController {

    @Autowired
    private CarAvailabilityService carAvailabilityService;

    // Add car availability
    @PostMapping("/add")
    public CarAvailability addCarAvailability(@RequestBody CarAvailability carAvailability) {
        return carAvailabilityService.saveCarAvailability(carAvailability);
    }

    // Get all car availabilities
    @GetMapping("/all")
    public List<CarAvailability> getAllCarAvailabilities() {
        return carAvailabilityService.getAllCarAvailabilities();
    }

    // Get car availability by ID
    @GetMapping("/one/{id}")
    public ResponseEntity<?> getCarAvailabilityById(@PathVariable("id") int id) {
        CarAvailability carAvailability = carAvailabilityService.getCarAvailabilityById(id);
        return carAvailability != null ? ResponseEntity.ok(carAvailability) : ResponseEntity.status(404).body("Car availability not found");
    }

    // Update car availability
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCarAvailability(@PathVariable("id") int id, @RequestBody CarAvailability carAvailability) {
        CarAvailability updatedCarAvailability = carAvailabilityService.updateCarAvailability(id, carAvailability);
        return ResponseEntity.ok(updatedCarAvailability);
    }

    // Delete car availability
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCarAvailability(@PathVariable("id") int id) {
        carAvailabilityService.deleteCarAvailability(id);
        return ResponseEntity.ok("Car availability with ID " + id + " has been deleted.");
    }
}
