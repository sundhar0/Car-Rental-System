package com.api.carrental.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.carrental.model.Complaint;

public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {
    List<Complaint> findByUser_UserId(int userId);

	List<Complaint> findByUserUserId(int userId);
}
