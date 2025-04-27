package com.api.carrental.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.carrental.model.CarTransaction;

public interface CarTransactionRepository extends JpaRepository<CarTransaction, Integer>{

}
