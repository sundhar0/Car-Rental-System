package com.api.carrental.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.carrental.model.Cancellation;

public interface CancellationRepository extends JpaRepository<Cancellation,Integer>{

}
