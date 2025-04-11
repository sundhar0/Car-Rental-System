package com.api.carrental.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rental_cars")
public class RentalCar {

    public enum CarStatus {
        AVAILABLE,
        RENTED_OUT,
        MAINTENANCE
    }

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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CarStatus carStatus;
    
    @Column(nullable = false)
    private double dailyRate;

    private Double weeklyRate;

    private Double monthlyRate;

    @Column(nullable = false)
    private double securityDeposit;

    private String additionalFees;
    
    @ManyToOne
    private RentalProvider rentalProvider;

    
    public double getDailyRate() {
		return dailyRate;
	}

	public void setDailyRate(double dailyRate) {
		this.dailyRate = dailyRate;
	}

	public Double getWeeklyRate() {
		return weeklyRate;
	}

	public void setWeeklyRate(Double weeklyRate) {
		this.weeklyRate = weeklyRate;
	}

	public Double getMonthlyRate() {
		return monthlyRate;
	}

	public void setMonthlyRate(Double monthlyRate) {
		this.monthlyRate = monthlyRate;
	}

	public double getSecurityDeposit() {
		return securityDeposit;
	}

	public void setSecurityDeposit(double securityDeposit) {
		this.securityDeposit = securityDeposit;
	}

	public String getAdditionalFees() {
		return additionalFees;
	}

	public void setAdditionalFees(String additionalFees) {
		this.additionalFees = additionalFees;
	}

    // Getters & Setters
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

    public CarStatus getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(CarStatus carStatus) {
        this.carStatus = carStatus;
    }

	public RentalProvider getRentalProvider() {
		return rentalProvider;
	}

	public void setRentalProvider(RentalProvider rentalProvider) {
		this.rentalProvider = rentalProvider;
	}



}
