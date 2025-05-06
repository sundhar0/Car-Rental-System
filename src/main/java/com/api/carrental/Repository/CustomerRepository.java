package com.api.carrental.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.carrental.model.Customer;
import com.api.carrental.model.User;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	 List<Customer> findByFullNameContainingIgnoreCase(String name);

	void deleteById(Long id);

	Optional<Customer> findById(long id);


	Optional<Customer> findByFullName(String fullName); 

}
