package com.api.carrental.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.carrental.model.CarDocuments;

public interface CarDocumentsRepository extends JpaRepository<CarDocuments, Integer>{

}
