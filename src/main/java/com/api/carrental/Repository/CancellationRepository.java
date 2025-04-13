package com.api.carrental.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.carrental.model.Booking;
import com.api.carrental.model.Cancellation;

public interface CancellationRepository extends JpaRepository<Cancellation,Integer>{

	Optional<Cancellation> findByBooking(Booking booking);




}
