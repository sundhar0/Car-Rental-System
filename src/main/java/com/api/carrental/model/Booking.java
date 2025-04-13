package com.api.carrental.model;

import com.api.carrental.enums.BookingStatus;

import com.api.carrental.enums.DriveMode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Booking {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	
	@ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @Column(nullable = false)
    private String bookingDate;

    @Column(nullable = false)
    private String returnDate;

    @Column(nullable = false)
    private double initialCost;
    
    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @Enumerated(EnumType.STRING)
    private DriveMode driveMode;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public double getInitialCost() {
		return initialCost;
	}

	public void setInitialCost(double initialCost) {
		this.initialCost = initialCost;
	}

	public BookingStatus getStatus() {
		return status;
	}

	public void setStatus(BookingStatus status) {
		this.status = status;
	}

	public DriveMode getDriveMode() {
		return driveMode;
	}

	public void setDriveMode(DriveMode driveMode) {
		this.driveMode = driveMode;

	}

	
    
    

}

    
    

