package com.api.carrental.model;

import java.time.LocalDate;

import org.hibernate.type.EnumType;

import com.api.carrental.enums.RideStatus;

import jakarta.persistence.*;

@Entity
public class RentalWithDriver {
	
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate rentalStart;

    private LocalDate rentalEnd;
    
    
    public RideStatus getRideStatus() {
		return rideStatus;
	}

	public void setRideStatus(RideStatus rideStatus) {
		this.rideStatus = rideStatus;
	}

	private RideStatus rideStatus;
    
    public RentalWithDriver() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getRentalStart() {
		return rentalStart;
	}

	public void setRentalStart(LocalDate rentalStart) {
		this.rentalStart = rentalStart;
	}

	public LocalDate getRentalEnd() {
		return rentalEnd;
	}

	public void setRentalEnd(LocalDate rentalEnd) {
		this.rentalEnd = rentalEnd;
	}

	
    
    
}
