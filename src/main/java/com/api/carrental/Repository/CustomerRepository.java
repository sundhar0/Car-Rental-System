package com.api.carrental.Repository;

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 477a9c7d1f831c0507906241b73e6083a73153db
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.carrental.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	 List<Customer> findByFullNameContainingIgnoreCase(String name);
<<<<<<< HEAD
=======
=======
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.carrental.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long>{

>>>>>>> dd77d92fd21284016937075bf41d3cac0b50ae04
>>>>>>> 477a9c7d1f831c0507906241b73e6083a73153db
}
