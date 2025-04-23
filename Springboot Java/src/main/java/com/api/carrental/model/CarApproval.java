package com.api.carrental.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class CarApproval {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;

    private LocalDate approvalDate = LocalDate.now();

    private boolean approved;

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

	

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public LocalDate getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(LocalDate approvalDate) {
		this.approvalDate = approvalDate;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean isApproved) {
		this.approved = isApproved;
	}
    
    
}