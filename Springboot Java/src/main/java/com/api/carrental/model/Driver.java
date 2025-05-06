package com.api.carrental.model;

import com.api.carrental.enums.DriverAvailability;

import jakarta.persistence.*;

@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int driverId;

    @ManyToOne
    private User user;
    
    private String profilePic;
    
    private String name;
    
    @Enumerated(EnumType.STRING)
    private DriverAvailability driverAvailability;

    private double totalEarnings;
    
    private int tripCompleted;
    
    private String licenseNo;
    
    private double rating;
    
    private int experienceYears;
    
    private String shortDescription;
    
    private double perDayCharge;

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	
	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}



	public DriverAvailability getDriverAvailability() {
		return driverAvailability;
	}

	public void setDriverAvailability(DriverAvailability driverAvailability) {
		this.driverAvailability = driverAvailability;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getExperienceYears() {
		return experienceYears;
	}

	public void setExperienceYears(int experienceYears) {
		this.experienceYears = experienceYears;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public double getPerDayCharge() {
		return perDayCharge;
	}

	public void setPerDayCharge(double perDayCharge) {
		this.perDayCharge = perDayCharge;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public double getTotalEarnings() {
		return totalEarnings;
	}

	public void setTotalEarnings(double totalEarnings) {
		this.totalEarnings = totalEarnings;
	}

	public int getTripCompleted() {
		return tripCompleted;
	}

	public void setTripCompleted(int tripCompleted) {
		this.tripCompleted = tripCompleted;
	}
    
	
    
}