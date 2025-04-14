package com.api.carrental.dto;

import org.springframework.stereotype.Component;

@Component
public class RefundRequsetDto {
	 private double refundAmount;
	 private String transactionId; // For tracking payment gateway or bank transaction
	public double getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(double refundAmount) {
		this.refundAmount = refundAmount;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	   
	    

}
