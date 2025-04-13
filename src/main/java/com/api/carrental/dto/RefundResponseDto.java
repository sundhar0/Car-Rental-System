package com.api.carrental.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.api.carrental.enums.RefundStatus;

@Component
public class RefundResponseDto {
	private int refundId;
    private int paymentId;
    private double refundAmount;
    private LocalDateTime refundDate;
    private String transactionId; // For tracking payment gateway or bank transaction
    private RefundStatus status;
	public int getRefundId() {
		return refundId;
	}
	public void setRefundId(int refundId) {
		this.refundId = refundId;
	}
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public double getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(double refundAmount) {
		this.refundAmount = refundAmount;
	}
	public LocalDateTime getRefundDate() {
		return refundDate;
	}
	public void setRefundDate(LocalDateTime refundDate) {
		this.refundDate = refundDate;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public RefundStatus getStatus() {
		return status;
	}
	public void setStatus(RefundStatus status) {
		this.status = status;
	}
    
    

}
