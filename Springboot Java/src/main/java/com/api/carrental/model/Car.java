package com.api.carrental.model;

import com.api.carrental.enums.CarSaleType;
import com.api.carrental.enums.CarStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Car {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	private String carMake;
    private String carModel;
    private String year;
    private String licensePlateNumber;
    private String vehicleRegistrationNumber;
    private String carColor;
    private CarStatus status;
    private CarSaleType carSaleType;
    
    public Car(int i, String string, String string2, double d, String string3, String string4, String string5,
			String string6, User user2, CarStatus available, CarSaleType rent) {
		// TODO Auto-generated constructor stub
	}
    
    public Car() {}
	public void setCarSaleType(CarSaleType carSaleType) {
		this.carSaleType = carSaleType;
	}
	@Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String fuelType;

    @Column(nullable = false)
    private String transmission;

    @Column(nullable = false)
    private String mileage;
    
    @ManyToOne
    private User carOwner;
    
    public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	private String model;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCarMake() {
		return carMake;
	}
	public void setCarMake(String carMake) {
		this.carMake = carMake;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getLicensePlateNumber() {
		return licensePlateNumber;
	}
	public void setLicensePlateNumber(String licensePlateNumber) {
		this.licensePlateNumber = licensePlateNumber;
	}
	public String getVehicleRegistrationNumber() {
		return vehicleRegistrationNumber;
	}
	public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
		this.vehicleRegistrationNumber = vehicleRegistrationNumber;
	}
	public String getCarColor() {
		return carColor;
	}
	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}
	public CarStatus getStatus() {
		return status;
	}
	public void setStatus(CarStatus string) {
		this.status = string;
	}
	
	public User getCarOwner() {
		return carOwner;
	}
	public void setCarOwner(User carOwner) {
		this.carOwner = carOwner;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public String getTransmission() {
		return transmission;
	}
	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}
	public String getMileage() {
		return mileage;
	}
	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	public CarSaleType getCarSaleType() {
		return carSaleType;
	}
	
	
	
    
}