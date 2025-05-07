package com.api.carrental.Controller;

import java.time.LocalDate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.carrental.Exception.CarNotAvailable;
import com.api.carrental.Exception.DriverNotAvailable;
import com.api.carrental.Exception.InvalidDateException;
import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Service.RentalWithDriverService;
import com.api.carrental.dto.RentalWithDriverDto;
import com.api.carrental.model.RentalWithDriver;
import com.api.carrental.enums.RideStatus;

@RestController
@RequestMapping("/api/rentWithDriver")
@CrossOrigin(origins = {"http://localhost:5173/"})
public class RentalWithDriverController {

    @Autowired
    private RentalWithDriverService rentalWithDriverService;
    
    @Autowired
    private RentalWithDriverDto dto;

    @PostMapping("/rent/{driverId}/{userId}/{carId}")
    public ResponseEntity<String> addRent(	
            @RequestBody RentalWithDriver rentalWithDriver,
            @PathVariable int driverId,
            @PathVariable int userId,
            @PathVariable int carId) throws InvalidIDException, DriverNotAvailable, CarNotAvailable {

        rentalWithDriverService.rentWithDriver(rentalWithDriver, driverId, carId, userId);
        return ResponseEntity.ok("Rental with driver added successfully");
    }

    @GetMapping("/getAll")
    public List<RentalWithDriver> getAll() {
        return rentalWithDriverService.getAll();
    }

    @GetMapping("/getById/{rentalId}")
    public RentalWithDriver getById(@PathVariable int rentalId) throws InvalidIDException {
        return rentalWithDriverService.getById(rentalId);
    }

    @GetMapping("/getByDriverId/{driverId}")
    public RentalWithDriver getByDriverId(@PathVariable int driverId) throws InvalidIDException {
        return rentalWithDriverService.getByDriverId(driverId);
    }

    @GetMapping("/getByUserId/{userId}")
    public RentalWithDriver getByUserId(@PathVariable int userId) throws InvalidIDException {
        return rentalWithDriverService.getByUserId(userId);
    }

    @GetMapping("/getByDriverName")
    public List<RentalWithDriver> getByDriverName(@RequestParam String name) throws DriverNotAvailable {
        return rentalWithDriverService.getByDriverName(name);
    }

    @GetMapping("/getByDate")
    public List<RentalWithDriver> getByDate(@RequestParam LocalDate dateFrom,
                                            @RequestParam LocalDate dateTo) throws InvalidDateException {
        return rentalWithDriverService.getByDate(dateFrom, dateTo);
    }

    @DeleteMapping("delete/{rentalId}")
    public ResponseEntity<String> deleteRentalWithDriver(@PathVariable int rentalId) throws InvalidIDException {
        rentalWithDriverService.deleteRentalWithDriver(rentalId);
        return ResponseEntity.ok("Rental with driver deleted successfully");
    }

    @PutMapping("/updateStatus/{rentalId}/{rideStatus}")
    public ResponseEntity<String> updateRideStatus(@PathVariable int rentalId,
                                                   @PathVariable RideStatus rideStatus) throws InvalidIDException {
        rentalWithDriverService.updateRideStatus(rentalId, rideStatus);
        return ResponseEntity.ok("Ride status updated successfully");
    }
    
    @GetMapping("/driver/{driverId}")
    public RentalWithDriverDto getRidesForDriver(
            @PathVariable int driverId,
            @RequestParam int page, 
            @RequestParam int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<RentalWithDriver> rentalPage = rentalWithDriverService.getRidesForDriver(driverId, pageable);
        
        dto.setList(rentalPage.getContent());
        dto.setCurrentPage(page);
        dto.setSize(size);
        dto.setTotalElements((int) rentalPage.getTotalElements());
        dto.setTotalPages(rentalPage.getTotalPages());
        return dto;
    }
}
