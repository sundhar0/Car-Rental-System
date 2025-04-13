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
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }


    public Customer getSingleCustomer(int id) throws InvalidIDException {
        Optional<Customer> optionalCustomer = customerRepository.findById((long) id);
        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        } else {
            throw new InvalidIDException("Customer with ID " + id + " not found.");
        }
    }
    
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
    
    public List<Customer> searchCustomersByName(String name) {
        return customerRepository.findByFullNameContainingIgnoreCase(name);
    }


	public Optional<Customer> getById(int ownId) {
		// TODO Auto-generated method stub
		return null;
	}


}
