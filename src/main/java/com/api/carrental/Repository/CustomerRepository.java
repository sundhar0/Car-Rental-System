package com.api.carrental.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.carrental.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
