package com.api.carrental.Repository;

<<<<<<< HEAD
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.carrental.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	 List<Customer> findByFullNameContainingIgnoreCase(String name);
=======
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.carrental.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long>{

>>>>>>> dd77d92fd21284016937075bf41d3cac0b50ae04
}
