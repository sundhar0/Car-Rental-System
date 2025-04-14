package com.api.carrental.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.carrental.model.RentalWithDriver;

public interface RentalWithDriverRepository extends JpaRepository<RentalWithDriver, Integer>{

	Optional<RentalWithDriver> findByDriverDriverId(int driverId);

	Optional<RentalWithDriver> findByDriverUserUserId(int userId);

	List<RentalWithDriver> findAllByDriverName(String name);


	List<RentalWithDriver> findByRentalStartLessThanEqualAndRentalEndGreaterThanEqual(LocalDate dateFrom,
			LocalDate dateTo);

}
