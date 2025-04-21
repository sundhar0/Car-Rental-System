package com.api.carrental.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Repository.CustomerRepository;
import com.api.carrental.model.Customer;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	

	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
    
    public Customer saveCustomer(Customer customer) {
    	//it will get all the customer details and store it in the customer table
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
    	// it will be used to show all the customer details
        return customerRepository.findAll();
    }


    public Customer getSingleCustomer(Long id) throws InvalidIDException {
    	//it will get all the customer details using customer id
        Optional<Customer> optionalCustomer = customerRepository.findById((long) id);
        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        } else {
            throw new InvalidIDException("Customer with ID " + id + " not found.");
        }
    }
    
    public void deleteCustomer(Long id) {
    	//it will delete the details of the customer using customer id
        customerRepository.deleteById(id);
    }
    
    public List<Customer> searchCustomersByName(String name) {
    	// it will be used to show te details of the customer using the customer name
        return customerRepository.findByFullNameContainingIgnoreCase(name);
    }
	

}
