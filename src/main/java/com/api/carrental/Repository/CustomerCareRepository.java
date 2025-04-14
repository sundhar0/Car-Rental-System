package com.api.carrental.Repository;

import com.api.carrental.model.CustomerCare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCareRepository extends JpaRepository<CustomerCare, Integer> {
}
