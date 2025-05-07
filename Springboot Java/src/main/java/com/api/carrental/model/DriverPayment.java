package com.api.carrental.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class DriverPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    private double amount;

    private LocalDate paymentDate;
    
    @ManyToOne
    private Manager updatedBy;

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate localDate) {
		this.paymentDate = localDate;
	}

	public Manager getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Manager updatedBy) {
		this.updatedBy = updatedBy;
	}

	

	
    
    
}	
