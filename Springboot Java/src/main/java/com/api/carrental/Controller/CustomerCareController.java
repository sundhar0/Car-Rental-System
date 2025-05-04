package com.api.carrental.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.carrental.Service.CustomerCareService;
import com.api.carrental.model.CustomerCare;
import com.api.carrental.Exception.InvalidIDException;

import java.util.List;

@RestController
@RequestMapping("/customer-care")
@CrossOrigin(origins = "http://localhost:5173")

public class CustomerCareController {

    @Autowired
    private CustomerCareService customerCareService;

    @PostMapping
    public ResponseEntity<String> addCustomerCare(@RequestBody CustomerCare customerCare) {
        customerCareService.addCustomerCare(customerCare);
        return ResponseEntity.ok("Customer Care request added successfully");
    }

    @PutMapping("/{supportId}")
    public ResponseEntity<String> updateCustomerCare(@PathVariable int supportId, @RequestBody CustomerCare updatedCustomerCare) {
        try {
            customerCareService.updateCustomerCare(supportId, updatedCustomerCare);
            return ResponseEntity.ok("Customer Care request updated successfully");
        } catch (InvalidIDException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{supportId}")
    public ResponseEntity<String> deleteCustomerCare(@PathVariable int supportId) {
        try {
            customerCareService.deleteCustomerCare(supportId);
            return ResponseEntity.ok("Customer Care request deleted successfully");
        } catch (InvalidIDException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{supportId}")
    public ResponseEntity<CustomerCare> getCustomerCareById(@PathVariable int supportId) {
        try {
            CustomerCare customerCare = customerCareService.getCustomerCareById(supportId);
            return ResponseEntity.ok(customerCare);
        } catch (InvalidIDException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<CustomerCare>> getAllCustomerCare() {
        List<CustomerCare> customerCares = customerCareService.getAllCustomerCare();
        return ResponseEntity.ok(customerCares);
    }
}
