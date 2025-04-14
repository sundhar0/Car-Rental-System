package com.api.carrental.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Repository.CarrentalRepository;
import com.api.carrental.Repository.CustomerRepository;
import com.api.carrental.Repository.FavoriteRepository;
import com.api.carrental.model.Car;
import com.api.carrental.model.Customer;
import com.api.carrental.model.Favorites;

@Service
public class FavoritesService {
	@Autowired
	private FavoriteRepository favoriteRepository;
	@Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CarrentalRepository carrentalRepository;
	
    @Autowired
    private CarService carService;
    @Autowired
    private CustomerService customerService;

	public List<Favorites> getAllFavorites() {
		return favoriteRepository.findAll();
	}


	public Favorites addFavorites(Favorites favorites) {
		int carId =favorites.getCar().getCarId();       // uses carId from Car entity
	    Long customerId = favorites.getCustomer().getId();
	    
	    /*fetch the car entity from database using carid ensure the car exist if not throws exception*/	    
	    Car car = carrentalRepository.findById(carId)
	        .orElseThrow(() -> new RuntimeException("Car not found"));
	    
	    /*fetch customer entity from db check if customer exist if not throws exception*/

	    Customer customer = customerRepository.findById(customerId)
	        .orElseThrow(() -> new RuntimeException("Customer not found"));
	    
	    //update booking with managed entity
	    favorites.setCar(car);
	    favorites.setCustomer(customer);
	    
		return favoriteRepository.save(favorites);
	}

	public Favorites addFavoritesforBuyer(Favorites favorites) throws InvalidIDException {
		int carId=favorites.getCar().getCarId();
		Long customerId=favorites.getCustomer().getId();
		Car car=carService.getById(carId);
		Customer customer=customerService.getSingleCustomer(customerId);
		favorites.setCar(car);
		favorites.setCustomer(customer);
		return favoriteRepository.save(favorites);
	}

	public Favorites getSingleFavorites(int id) throws InvalidIDException {
		return favoriteRepository.findById(id)
                .orElseThrow(() -> new InvalidIDException("Favorite with ID " + id + " not found"));
	}
	
	public Favorites updateFavorites(Favorites favorites) {
        // Validate and fetch car if provided
        if (favorites.getCar() != null && favorites.getCar().getCarId() != 0) {
            Car car = carrentalRepository.findById(favorites.getCar().getCarId())
                    .orElseThrow(() -> new RuntimeException("Car with ID " + favorites.getCar().getCarId() + " not found"));
            favorites.setCar(car);
        }

        // Validate and fetch customer if provided
        if (favorites.getCustomer() != null && favorites.getCustomer().getId() != null) {
            Customer customer = customerRepository.findById(favorites.getCustomer().getId())
                    .orElseThrow(() -> new RuntimeException("Customer with ID " + favorites.getCustomer().getId() + " not found"));
            favorites.setCustomer(customer);
        }

        return favoriteRepository.save(favorites);
    }


	public void hardDelete(Favorites favorites) {
		favoriteRepository.delete(favorites);
		
	}


	public Favorites updateByBuyer(Favorites favorites) throws InvalidIDException {
		Car car=carService.getById(favorites.getCar().getCarId());
		Customer customer=customerService.getSingleCustomer(favorites.getCustomer().getId());
		favorites.setCar(car);
		favorites.setCustomer(customer);
		return favoriteRepository.save(favorites);
	}


	


	
	
	

}
