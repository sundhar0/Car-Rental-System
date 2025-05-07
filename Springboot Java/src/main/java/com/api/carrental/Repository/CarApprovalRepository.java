package com.api.carrental.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.carrental.model.CarApproval;

public interface CarApprovalRepository extends JpaRepository<CarApproval, Integer>{

	List<CarApproval> findByApprovedTrue();

	List<CarApproval> findByApprovedTrueAndId(int id);
	
}
