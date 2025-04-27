package com.api.carrental.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Service.RentalHistoryService;
import com.api.carrental.model.RentalHistory;

@RestController
@RequestMapping("/api/history")
public class RentalHistoryController {
	
	@Autowired
    private RentalHistoryService rentalHistoryService;

    @PostMapping("/add")
    public RentalHistory createRentalHistory(@RequestBody RentalHistory rentalHistory) {
        return rentalHistoryService.addRentalHistory(rentalHistory);
    }
    
    @GetMapping("/getall")
    public List<RentalHistory> getAllRentalHistories() {
        return rentalHistoryService.getAllRentalHistories();
    }
    
    @DeleteMapping("/{id}")
    public String deleteRentalHistory(@PathVariable int id) throws InvalidIDException {
        rentalHistoryService.deleteRentalHistory(id);
        return "Rental history with ID " + id + " has been deleted.";
    }

}
