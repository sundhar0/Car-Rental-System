package com.api.carrental.model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "rental_provider")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private String driversLicenseNumber;
    private String identityDocumentPath;
    private String profilePhotoPath;

    public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

public Customer(int id, String fullName, String email, String phoneNumber, String address, String driversLicenseNumber,
		String identityDocumentPath, String profilePhotoPath) {
	super();
	this.id = id;
	this.fullName = fullName;
	this.email = email;
	this.phoneNumber = phoneNumber;
	this.address = address;
	this.driversLicenseNumber = driversLicenseNumber;
	this.identityDocumentPath = identityDocumentPath;
	this.profilePhotoPath = profilePhotoPath;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getFullName() {
	return fullName;
}

public void setFullName(String fullName) {
	this.fullName = fullName;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPhoneNumber() {
	return phoneNumber;
}

public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getDriversLicenseNumber() {
	return driversLicenseNumber;
}

public void setDriversLicenseNumber(String driversLicenseNumber) {
	this.driversLicenseNumber = driversLicenseNumber;
}

public String getIdentityDocumentPath() {
	return identityDocumentPath;
}

public void setIdentityDocumentPath(String identityDocumentPath) {
	this.identityDocumentPath = identityDocumentPath;
}

public String getProfilePhotoPath() {
	return profilePhotoPath;
}

public void setProfilePhotoPath(String profilePhotoPath) {
	this.profilePhotoPath = profilePhotoPath;
}
    

    
}