package com.api.carrental.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.carrental.model.DriverDocument;

public interface DriverDocumentRepository extends JpaRepository<DriverDocument, Integer> {
    DriverDocument findByDriverDriverId(int driverId);
}
