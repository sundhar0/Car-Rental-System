		package com.api.carrental.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class DriverSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int scheduleId;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    private LocalDate availableFrom;

    private LocalDate availableTo;
    

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public LocalDate getAvailableFrom() {
		return availableFrom;
	}

	public void setAvailableFrom(LocalDate availableFrom) {
		this.availableFrom = availableFrom;
	}

	public LocalDate getAvailableTo() {
		return availableTo;
	}

	public void setAvailableTo(LocalDate availableTo) {
		this.availableTo = availableTo;
	}
    
    
}
