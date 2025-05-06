package com.api.carrental.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.carrental.model.Rental;

public interface RentalRepository extends JpaRepository<Rental,Integer>{

}
