package com.api.carrental.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.carrental.model.RentalCar;

@Repository
public interface RentalCarRepository extends JpaRepository<RentalCar, Integer> {

}
