package com.api.carrental.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.carrental.model.RentalHistory;

public interface RentalHistoryRepository extends JpaRepository<RentalHistory, Integer>{

}
