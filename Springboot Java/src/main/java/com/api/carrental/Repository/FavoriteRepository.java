package com.api.carrental.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.api.carrental.model.Favorites;

public interface FavoriteRepository extends JpaRepository<Favorites,Integer>{


}
