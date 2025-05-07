package com.api.carrental.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Repository.PaymentRepository;
import com.api.carrental.Repository.RefundRepository;
import com.api.carrental.dto.RefundRequsetDto;
import com.api.carrental.dto.RefundResponseDto;
import com.api.carrental.enums.RefundStatus;
import com.api.carrental.model.Payment;
import com.api.carrental.model.Refund;

@Service
public class RefundService {
	@Autowired
    private RefundRepository refundRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    // Initiate a new refund
	public RefundResponseDto initiateRefund(int paymentId, RefundRequsetDto request) throws InvalidIDException {
		// Find the associated payment
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new InvalidIDException("Payment ID not found"));

        // Create a new Refund object
        Refund refund = new Refund();
        refund.setPayment(payment);
        refund.setRefundAmount(request.getRefundAmount());
        refund.setTransactionId(request.getTransactionId());
        refund.setRefundDate(LocalDateTime.now());
        refund.setStatus(RefundStatus.PENDING); // Initial status is PENDING

        // Save the refund to the database
        Refund saved = refundRepository.save(refund);

        // Convert to response DTO and return
        return mapToResponse(saved);
    }

    // Get a specific refund by its ID
    public RefundResponseDto getRefundById(int id) throws InvalidIDException {
        Refund refund = refundRepository.findById(id)
                .orElseThrow(() -> new InvalidIDException("Refund ID not found"));

        return mapToResponse(refund);
	}
 // Map a Refund entity to a Response DTO
    private RefundResponseDto mapToResponse(Refund refund) {
        RefundResponseDto dto = new RefundResponseDto();
        dto.setRefundId(refund.getRefundId());
        dto.setPaymentId(refund.getPayment().getId());
        dto.setRefundAmount(refund.getRefundAmount());
        dto.setTransactionId(refund.getTransactionId());
        dto.setStatus(refund.getStatus());
        dto.setRefundDate(refund.getRefundDate());
        return dto;
    }



}
