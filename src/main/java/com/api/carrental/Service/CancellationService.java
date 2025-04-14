package com.api.carrental.Service;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Repository.BookingRepository;
import com.api.carrental.Repository.CancellationRepository;
import com.api.carrental.model.Booking;
import com.api.carrental.model.Cancellation;
import com.api.carrental.model.TestDrive;


@Service
public class CancellationService {
	@Autowired
	private CancellationRepository cancellationRepository;
	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private TestDriveService testDriveService;


    public Cancellation cancelBooking(Cancellation cancellation) {
    	cancellation.setCancelledDate(LocalDateTime.now());
        return cancellationRepository.save(cancellation);
    }

    public List<Cancellation> getAllCancellations() {
        return cancellationRepository.findAll();
    }

    public Cancellation getCancellationByBookingId(int bookingId) throws InvalidIDException {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new InvalidIDException("Booking ID not found."));

        return cancellationRepository.findByBooking(booking)
                .orElseThrow(() -> new InvalidIDException("Cancellation not found for booking."));
    }

	public Cancellation getCancellationByTestDriveId(int tdId) throws InvalidIDException {
		TestDrive testDrive=testDriveService.findById(tdId);
		Cancellation cancellation=cancellationRepository.finbyTestDrive(testDrive);
		if(cancellation==null)
			throw new InvalidIDException("Given TestDrive Id is Invalid...");
		return cancellation;
	}

}
