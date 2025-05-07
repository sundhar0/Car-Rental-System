package com.api.carrental.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.carrental.model.Booking;
import com.api.carrental.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment,Integer>{


	Optional<Booking> findByBookingId(int id);

	Optional<Booking> findByBooking(Booking booking);

}
