package com.api.carrental.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger=LoggerFactory.getLogger("CustomerCareService");
    //Adding the details in the customer care table
    public void addCustomerCare(CustomerCare customerCare) {
        customerCareRepository.save(customerCare);
        logger.info("Added...");
    }
    //Updating the details in the table
    //fetching the details and storing it in the table
    //save the table
    public void updateCustomerCare(int supportId, CustomerCare updatedCustomerCare) throws InvalidIDException {
        Optional<CustomerCare> optionalCustomerCare = customerCareRepository.findById(supportId);
        if (optionalCustomerCare.isEmpty()) {
            throw new InvalidIDException("Customer Care request not found");
        }
        updatedCustomerCare.setSupportId(supportId);
        logger.info("Details updated..");
        customerCareRepository.save(updatedCustomerCare);
    }
    //deleting the details of the table
    //fetching the details by using its id
    //verify the value and delete the details
    public void deleteCustomerCare(int supportId) throws InvalidIDException {
        Optional<CustomerCare> optionalCustomerCare = customerCareRepository.findById(supportId);
        if (optionalCustomerCare.isEmpty()) {
            throw new InvalidIDException("Customer Care request not found");
        }
        logger.info("Details Deleted...");
        customerCareRepository.deleteById(supportId);
    }
    //get the details by the customer care id
    //if its valid means show the details
    public CustomerCare getCustomerCareById(int supportId) throws InvalidIDException {
        Optional<CustomerCare> optionalCustomerCare = customerCareRepository.findById(supportId);
        if (optionalCustomerCare.isEmpty()) {
            throw new InvalidIDException("Customer Care request not found");
        }
        return optionalCustomerCare.get();
    }
    //fetching all the values 
    //use findAll method
    public List<CustomerCare> getAllCustomerCare() {
        return customerCareRepository.findAll();
    }
}
