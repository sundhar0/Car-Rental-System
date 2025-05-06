package com.api.carrental.Repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.api.carrental.model.Booking;

public interface BookingRepository extends JpaRepository<Booking,Integer>{

	

}
