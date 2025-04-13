package com.api.carrental.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.carrental.model.RentalWithDriver;

public interface RentalWithDriverRepository extends JpaRepository<RentalWithDriver, Integer>{

}
