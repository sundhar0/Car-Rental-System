package com.api.carrental.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.carrental.Exception.InvalidIDException;
import com.api.carrental.Service.FavoritesService;
import com.api.carrental.dto.MessageResponseDto;
import com.api.carrental.model.Favorites;

@RestController
@RequestMapping("api/fav")
public class FavoriteController {
	@Autowired
	private FavoritesService favoritesService;
	@Autowired
	private MessageResponseDto messageDto;
	
	@GetMapping("/getall")
	public List<Favorites> getAllFovorites() {
 		return favoritesService.getAllFavorites();
 	}
	
	@PostMapping("/add")
	public Favorites addFavorite(@RequestBody Favorites favorites) {
		return favoritesService.addFavorites(favorites);
	}
	
	@PutMapping("/update/{id}")
    public ResponseEntity<?> updateFavorites(@PathVariable int id, @RequestBody Favorites newValue) {
        try {
            // Fetch existing favorites by ID
            Favorites existingFavorites = favoritesService.getSingleFavorites(id);

            // Update car if provided in request body
            if (newValue.getCar() != null && newValue.getCar().getCarId() != 0) {
                existingFavorites.setCar(newValue.getCar());
            }

            // Update customer if provided in request body
            if (newValue.getCustomer() != null && newValue.getCustomer().getId() != 0) {
                existingFavorites.setCustomer(newValue.getCustomer());
            }

            // Save updated favorites
            Favorites updatedFavorites = favoritesService.updateFavorites(existingFavorites);
            return ResponseEntity.ok(updatedFavorites);

        } catch (InvalidIDException e) {
            messageDto.setBody(e.getMessage());
            messageDto.setStatusCode(400);
            return ResponseEntity.status(400).body(messageDto);
        } catch (RuntimeException e) {
            messageDto.setBody("Update failed: " + e.getMessage());
            messageDto.setStatusCode(400);
            return ResponseEntity.status(400).body(messageDto);
        }
    }
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> hardDeleteFavorites(@PathVariable int id) {
	    try {
	        // Validate ID and fetch the favorites object
	        Favorites favorites = favoritesService.getSingleFavorites(id);

	        // Perform deletion
	        favoritesService.hardDelete(favorites);

	        messageDto.setBody("Favorites record hard deleted from DB!!");
	        messageDto.setStatusCode(200);
	        return ResponseEntity.ok(messageDto);

	    } catch (InvalidIDException e) {
	        messageDto.setBody(e.getMessage());
	        messageDto.setStatusCode(400);
	        return ResponseEntity.status(400).body(messageDto);
	    }
	}


 

}
