package com.api.carrental.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Repository.CustomerCareRepository;
import com.api.carrental.model.CustomerCare;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerCareService {

    @Autowired
    private CustomerCareRepository customerCareRepository;

    public void addCustomerCare(CustomerCare customerCare) {
        customerCareRepository.save(customerCare);
    }

    public void updateCustomerCare(int supportId, CustomerCare updatedCustomerCare) throws InvalidIDException {
        Optional<CustomerCare> optionalCustomerCare = customerCareRepository.findById(supportId);
        if (optionalCustomerCare.isEmpty()) {
            throw new InvalidIDException("Customer Care request not found");
        }
        updatedCustomerCare.setSupportId(supportId);
        customerCareRepository.save(updatedCustomerCare);
    }

    public void deleteCustomerCare(int supportId) throws InvalidIDException {
        Optional<CustomerCare> optionalCustomerCare = customerCareRepository.findById(supportId);
        if (optionalCustomerCare.isEmpty()) {
            throw new InvalidIDException("Customer Care request not found");
        }
        customerCareRepository.deleteById(supportId);
    }

    public CustomerCare getCustomerCareById(int supportId) throws InvalidIDException {
        Optional<CustomerCare> optionalCustomerCare = customerCareRepository.findById(supportId);
        if (optionalCustomerCare.isEmpty()) {
            throw new InvalidIDException("Customer Care request not found");
        }
        return optionalCustomerCare.get();
    }

    public List<CustomerCare> getAllCustomerCare() {
        return customerCareRepository.findAll();
    }
}
