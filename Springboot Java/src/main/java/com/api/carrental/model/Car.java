package com.api.carrental.model;
 

import com.api.carrental.enums.CarSaleType;
import com.api.carrental.enums.CarStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int carId;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String year;

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
    
    private String carMake;
    private String licensePlateNumber;
    private String vehicleRegistrationNumber;
    private String carColor;
    private String status;
    
    @ManyToOne
    private Customer customer;
    
    @ManyToOne
    private CarDocuments carDocuments;
    
    @Enumerated(EnumType.STRING)
    private CarStatus carStatus;

    @Enumerated(EnumType.STRING)
    private CarSaleType carSaleType;

	public Car() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Car(int carId, String model, String year, double price, String brand, String fuelType, String transmission,
			String mileage, String carMake, String licensePlateNumber, String vehicleRegistrationNumber,
			String carColor, String status, Customer customer, CarDocuments carDocuments, CarStatus carStatus,
			CarSaleType carSaleType) {
		super();
		this.carId = carId;
		this.model = model;
		this.year = year;
		this.price = price;
		this.brand = brand;
		this.fuelType = fuelType;
		this.transmission = transmission;
		this.mileage = mileage;
		this.carMake = carMake;
		this.licensePlateNumber = licensePlateNumber;
		this.vehicleRegistrationNumber = vehicleRegistrationNumber;
		this.carColor = carColor;
		this.status = status;
		this.customer = customer;
		this.carDocuments = carDocuments;
		this.carStatus = carStatus;
		this.carSaleType = carSaleType;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
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

	public String getCarMake() {
		return carMake;
	}

	public void setCarMake(String carMake) {
		this.carMake = carMake;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CarDocuments getCarDocuments() {
		return carDocuments;
	}

	public void setCarDocuments(CarDocuments carDocuments) {
		this.carDocuments = carDocuments;
	}

	public CarStatus getCarStatus() {
		return carStatus;
	}

	public void setCarStatus(CarStatus carStatus) {
		this.carStatus = carStatus;
	}

	public CarSaleType getCarSaleType() {
		return carSaleType;
	}

	public void setCarSaleType(CarSaleType carSaleType) {
		this.carSaleType = carSaleType;
	}

    

}
