package com.api.carrental.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Service.CustomerService;
import com.api.carrental.Service.RentalHistoryService;
import com.api.carrental.model.Customer;
import com.api.carrental.model.RentalHistory;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/history")
public class RentalHistoryController {
	
	@Autowired
    private RentalHistoryService rentalHistoryService;

	@Autowired
	private CustomerService customerService;

	@PostMapping("/add")
	public RentalHistory createRentalHistory(@RequestBody RentalHistory rentalHistory) {
	    Customer customer = rentalHistory.getCustomer();

	    // Ensure the customer is associated with a user
	    if (customer.getUser() != null && customer.getUser().getUserId() != 0) {
	        int userId = customer.getUser().getUserId();

	        if (customer.getId() == 0) {
	            // Save the customer and assign the saved instance back to the rental history
	            Customer savedCustomer = customerService.saveCustomer(customer, userId);
	            rentalHistory.setCustomer(savedCustomer);
	        } else {
	            // Just make sure user is fully attached if customer already exists
	            customer.setUser(customerService.getById(customer.getId()).get().getUser());
	            rentalHistory.setCustomer(customer);
	        }
	    } else {
	        throw new RuntimeException("User information is required for the customer.");
	    }

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
