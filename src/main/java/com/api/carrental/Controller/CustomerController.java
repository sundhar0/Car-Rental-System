package com.api.carrental.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.api.carrental.Service.CustomerService;
import com.api.carrental.model.Customer;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/customer")
public class CustomerController {

	 @Autowired
	    private CustomerService customerService;

	//To check working
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello - Customer Module!";
    }
    
    //Add customer 
    @PostMapping("/add/{userId}")
    public Customer addCustomer (@PathVariable int userId,@RequestBody Customer customer) {
        return customerService.saveCustomer(customer,userId);
    }

    //Get all customers
    @GetMapping("/all")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    //Get customer by id
    @GetMapping("/one/{id}") 
    public ResponseEntity<?> getSingleCustomer(@PathVariable("id") int id) {
        try {
            Customer customer = customerService.getSingleCustomer(id);
            return ResponseEntity.ok(customer); 
        } catch (InvalidIDException e) {

            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    //Update customer
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") int id,
                                            @RequestBody Customer newValue) {
        try {
            Customer customer = customerService.getSingleCustomer(id);

            if (newValue.getPhoneNumber() != null)
                customer.setPhoneNumber(newValue.getPhoneNumber());

            if (newValue.getAddress() != null)
                customer.setAddress(newValue.getAddress());

            customer = customerService.saveCustomer(customer, id);
            return ResponseEntity.ok(customer);

        } catch (InvalidIDException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    
    
    //Search Customers by name
    @GetMapping("/search")
    public ResponseEntity<List<Customer>> searchCustomersByName(@RequestParam("name") String name) {
        List<Customer> customers = customerService.searchCustomersByName(name);
        return ResponseEntity.ok(customers);
    }
    
    //Delete Customers by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long id) {
        try {
            customerService.deleteCustomer(id);
            return ResponseEntity.ok("Customer with ID " + id + " has been deleted.");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Failed to delete customer: " + e.getMessage());
        }
    }
    
    @PostMapping("/upload/{cid}")
    public Customer uploadCustomerPic(@PathVariable int cid, @RequestParam MultipartFile file)
            throws IOException, InvalidIDException {
        return customerService.uploadImage(file, cid);
    }




}
