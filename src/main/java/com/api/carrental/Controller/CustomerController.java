package com.api.carrental.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.carrental.Service.CustomerService;
import com.api.carrental.model.Customer;

@RestController
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/api/customer/add")
 	public Customer addCustomer(@RequestBody Customer customer) {
 		return customerService.addCustomer(customer);
 	}

}
