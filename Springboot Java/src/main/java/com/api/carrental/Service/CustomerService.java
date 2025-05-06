package com.api.carrental.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Repository.CustomerRepository;
import com.api.carrental.Repository.UserRepository;
import com.api.carrental.model.Customer;
import com.api.carrental.model.User;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private UserRepository userRepository;
	

	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
    
<<<<<<< HEAD:src/main/java/com/api/carrental/Service/CustomerService.java
	public Customer saveCustomer(Customer customer, int userId) {
	    Optional<User> optionalUser = userRepository.findById(userId);
	    if (optionalUser.isPresent()) {
	        customer.setUser(optionalUser.get()); // ✅ link user to customer
	        return customerRepository.save(customer);
	    } else {
	        throw new RuntimeException("User not found with ID: " + userId);
	    }
	}
=======
    public Customer saveCustomer(Customer customer) {
    	//it will get all the customer details and store it in the customer table
        return customerRepository.save(customer);
    }
>>>>>>> 1a6859e55dacab412dbafd30a213695588396b9d:Springboot Java/src/main/java/com/api/carrental/Service/CustomerService.java

    public List<Customer> getAllCustomers() {
    	// it will be used to show all the customer details
        return customerRepository.findAll();
    }


    public Customer getSingleCustomer(Long cId) throws InvalidIDException {
    	//it will get all the customer details using customer id
        Optional<Customer> optionalCustomer = customerRepository.findById((long) cId);
        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        } else {
            throw new InvalidIDException("Customer with ID " + cId + " not found.");
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
<<<<<<< HEAD:src/main/java/com/api/carrental/Service/CustomerService.java


	public Optional<Customer> getById(int ownId) {
		return customerRepository.findById(ownId);
	}


	public Customer getCustomerById(int customerId) throws InvalidIDException {
	    return customerRepository.findById(customerId)
	            .orElseThrow(() -> new InvalidIDException("Customer not found with ID: " + customerId));
	}


	public Customer uploadImage(MultipartFile file, int cid) throws InvalidIDException, IOException {
		Customer customer = customerRepository.findById(cid)
	            .orElseThrow(() -> new InvalidIDException("Invalid Customer ID"));

	    List<String> allowedExtensions = Arrays.asList("png", "jpg", "jpeg", "gif", "svg");
	    String originalFileName = file.getOriginalFilename();
	    String extension = originalFileName.split("\\.")[1];

	    if (!allowedExtensions.contains(extension)) {
	        throw new RuntimeException("Invalid Image Type");
	    }

	    String uploadPath = "C:\\Users\\Varshaa\\OneDrive\\Pictures\\customer1.png";
	    Files.createDirectories(Paths.get(uploadPath));
	    Path path = Paths.get(uploadPath + "\\" + originalFileName);
	    Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

	    customer.setProfilePhotoPath(path.toString());
	    return customerRepository.save(customer);
	}



	


	

	


=======
>>>>>>> 1a6859e55dacab412dbafd30a213695588396b9d:Springboot Java/src/main/java/com/api/carrental/Service/CustomerService.java
	

}
