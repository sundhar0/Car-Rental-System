package com.api.carrental.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.carrental.model.RentalHistory;

public interface RentalHistoryRepository extends JpaRepository<RentalHistory, Integer>{

	Page<RentalHistory> findByCustomerId(int customerId, Pageable pageable);

}

