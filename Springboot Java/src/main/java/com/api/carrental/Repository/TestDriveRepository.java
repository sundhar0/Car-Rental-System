package com.api.carrental.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.carrental.model.TestDrive;

public interface TestDriveRepository extends JpaRepository<TestDrive, Integer> {

	List<TestDrive> findAllById(int id);

	String deleteByCarId(int carId);

}
