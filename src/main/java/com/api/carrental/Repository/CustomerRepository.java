package com.api.carrental.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.carrental.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long>{

}
