	package com.api.carrental.Service;
	
	import java.util.Optional;
	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;
	
	import com.api.carrental.Exception.InvalidIDException;
	import com.api.carrental.Repository.BookingRepository;
	import com.api.carrental.Repository.PaymentRepository;
	import com.api.carrental.dto.PaymentRequestDto;
	import com.api.carrental.dto.PaymentResponseDto;
	import com.api.carrental.enums.PaymentStatus;
	import com.api.carrental.model.Booking;
	import com.api.carrental.model.Payment;
	@Service
	public class PaymentService {
		@Autowired
	    private PaymentRepository paymentRepository;
	
	    @Autowired
	    private BookingRepository bookingRepository;
	
		public PaymentResponseDto initiatePayment(PaymentRequestDto dto) throws InvalidIDException {
			Optional<Booking> bookingOptional = bookingRepository.findById(dto.getBookingId());
	
	        if (!bookingOptional.isPresent()) {
	            throw new InvalidIDException("Booking ID not found.");
	        }
	
	        Booking booking = bookingOptional.get();
	
	        Payment payment = new Payment();
	        payment.setBooking(booking);
	        payment.setAmountPaid(dto.getAmountPaid());
	        payment.setPaymentDate(dto.getPaymentDate());
	        payment.setPaymentMode(dto.getPaymentMode());
	        payment.setStatus(PaymentStatus.SUCCESS);
	        Payment saved = paymentRepository.save(payment);
	
	        return mapToDto(saved);
		}
	
		public PaymentResponseDto getPaymentById(int id) throws InvalidIDException {
			Payment payment = paymentRepository.findById(id)
	                .orElseThrow(() -> new InvalidIDException("Payment ID not found"));
	
	        return mapToDto(payment);
		}
		
		private PaymentResponseDto mapToDto(Payment payment) {
	        PaymentResponseDto dto = new PaymentResponseDto();
	        dto.setId(payment.getId());
	        dto.setBookingId(payment.getBooking().getId());
	        dto.setAmountPaid(payment.getAmountPaid());
	        dto.setPaymentDate(payment.getPaymentDate());
	        dto.setPaymentMode(payment.getPaymentMode());
	        dto.setStatus(payment.getStatus());
	        return dto;
	    }
		
		public PaymentResponseDto confirmPayment(int id) throws InvalidIDException {
		    Payment payment = paymentRepository.findById(id)
		            .orElseThrow(() -> new InvalidIDException("Payment ID not found"));

		    payment.setStatus(PaymentStatus.SUCCESS);
		    paymentRepository.save(payment);
		    return mapToDto(payment);
		}

		public String verifyPayment(int id) throws InvalidIDException {
		    Payment payment = paymentRepository.findById(id)
		            .orElseThrow(() -> new InvalidIDException("Payment ID not found"));

		    return "Payment status: " + payment.getStatus().name();
		}

	
	}
