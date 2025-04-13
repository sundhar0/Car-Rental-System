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
    
    @ManyToOne
    private User carOwner;
    
    @Enumerated(EnumType.STRING)
    private CarStatus carStatus;

    @Enumerated(EnumType.STRING)
    private CarSaleType carSaleType;

    

	public Car() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Car(int carId, String model, String year, double price, String brand, String fuelType, String transmission,
			String mileage, User carOwner, CarStatus carStatus, CarSaleType carSaleType) {
		super();
		this.carId = carId;
		this.model = model;
		this.year = year;
		this.price = price;
		this.brand = brand;
		this.fuelType = fuelType;
		this.transmission = transmission;
		this.mileage = mileage;
		this.carOwner = carOwner;
		this.carStatus = carStatus;
		this.carSaleType = carSaleType;
	}

	public CarSaleType getCarSaleType() {
		return carSaleType;
	}

	public void setCarSaleType(CarSaleType carSaleType) {
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

	public User getCarOwner() {
		return carOwner;
	}

	public void setCarOwner(User carOwner) {
		this.carOwner = carOwner;
	}

	public CarStatus getCarStatus() {
		return carStatus;
	}

	public void setCarStatus(CarStatus carStatus) {
		this.carStatus = carStatus;
	}

	
    
    


}
