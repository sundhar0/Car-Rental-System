package com.api.carrental.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.carrental.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	 List<Customer> findByFullNameContainingIgnoreCase(String name);

	Optional<Customer> findById(Long customerId);

}
